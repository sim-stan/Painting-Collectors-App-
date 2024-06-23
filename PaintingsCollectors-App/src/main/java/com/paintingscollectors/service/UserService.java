package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegisterDTO;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    public boolean login(UserLoginDTO userLoginDTO) {

        Optional<User> byUsername = userRepository.findByUsername(userLoginDTO.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        User user = byUsername.get();

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), byUsername.get().getPassword())) {
            return false;
        }
        loggedUser.setId(user.getId());
        loggedUser.setUsername(user.getUsername());
        loggedUser.setLogged(true);
        System.out.println(loggedUser);
        return true;
    }


    public boolean register(UserRegisterDTO userRegisterDTO) {


        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        boolean existsByUsernameOrEmail = this.userRepository.existsByUsernameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());


        if (existsByUsernameOrEmail) {
            return false;
        }

        User user=modelMapper.map(userRegisterDTO,User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(user);
        return true;
    }
}
