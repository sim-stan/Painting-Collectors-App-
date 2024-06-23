package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.model.dto.PaintingInfoDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaintingService {


    private final UserRepository userRepository;
    private final PaintingRepository paintingRepository;
    private final StyleRepository styleRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public PaintingService(UserRepository userRepository, PaintingRepository paintingRepository, StyleRepository styleRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.paintingRepository = paintingRepository;
        this.styleRepository = styleRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    public boolean addPainting(AddPaintingDTO addPaintingDTO) {

        if (!loggedUser.isLogged()) {
            return false;
        }

        Optional<Style> style = styleRepository.findByStyleName(addPaintingDTO.getStyleName());
        if (style.isEmpty()) {
            return false;
        }

        Optional<User> user = userRepository.findById(loggedUser.getId());

        if (user.isEmpty()) {
            return false;
        }

        Painting painting = new Painting();
        painting.setName(addPaintingDTO.getName());
        painting.setAuthor(addPaintingDTO.getAuthor());
        painting.setImageURL(addPaintingDTO.getImageURL());
        painting.setStyle(style.get());
        painting.setOwner(user.get());
        painting.setVotesCount(0);


        paintingRepository.save(painting);
        return true;
    }

    @Transactional
    public List<Painting> findAllOtherPaintings(long id) {

        return paintingRepository.findAll().stream()
                .filter(painting -> !painting.getOwner().getId().equals(id)).toList();
    }

    @Transactional
    public List<Painting> findAllMyPaintings(long id) {

        return paintingRepository.findAll().stream()
                .filter(painting -> painting.getOwner().getId().equals(id)).toList();
    }

    @Transactional
    public List<Painting> findAllMyFavoritePaintings(long id) {
        return userRepository.findById(id)
                .map(User::getFavoritePaintings)
                .orElseGet(ArrayList::new);
    }

    @Transactional
    public void deletePainting(long id, long pantingId) {
        Optional<Painting> painting = paintingRepository.findById(pantingId);

        if (painting.isEmpty()) {
            return;
        }

        if (painting.get().isFavorite()){
            return;
        }

        if (painting.get().getVotesCount()!=0){
            return;
        }

        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isEmpty()) {
            return;
        }

        User user = optUser.get();
        List<Painting> allMyPaints = user.getMyPaintings();
        allMyPaints.remove(painting.get());
        paintingRepository.deleteById(pantingId);
        userRepository.save(user);


    }

    @Transactional
    public void deletePaintingFromFavorite(long id, long pantingId) {
        Optional<Painting> painting = paintingRepository.findById(pantingId);

        if (painting.isEmpty()) {
            return;
        }

        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isEmpty()) {
            return;
        }

        User user = optUser.get();
        user.getFavoritePaintings().remove(painting.get());
        userRepository.save(user);


    }

    @Transactional
    public void addToFavourites(long id, long paintingId) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return;
        }

        Optional<Painting> painting = paintingRepository.findById(paintingId);

        if (painting.isEmpty()) {
            return;
        }

        List<Painting> favorites = userOpt.get().getFavoritePaintings();
        if (favorites.contains(painting.get())) {
            return;
        }

        userOpt.get().addFavourite(painting.get());

        userRepository.save(userOpt.get());

        painting.get().setFavorite(true);

    }

    @Transactional
    public void voteForPainting(long id) {

        Optional<Painting> painting = paintingRepository.findById(id);
        User user = userRepository.findById(loggedUser.getId()).get();

        if (painting.isEmpty() || painting.get().getOwner().getId().equals(user.getId())) {
            return;
        }

        if (user.getRatedPaintings().contains(painting.get())) {
            return;
        }

        user.getRatedPaintings().add(painting.get());
        painting.get().addVote();
        paintingRepository.save(painting.get());

    }

    public List<Painting> findMostRatedPaintings() {
       return paintingRepository.findAll().stream()
               .sorted(Comparator.comparing(Painting::getVotesCount).reversed())
               .sorted(Comparator.comparing(Painting::getName))
               .limit(2)
               .map(p->modelMapper.map(p,Painting.class))
               .toList();
   }

}
