package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.PaintingInfoDTO;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final PaintingService paintingService;

    public HomeController(LoggedUser loggedUser, PaintingService paintingService) {
        this.loggedUser = loggedUser;
        this.paintingService = paintingService;
    }


    @GetMapping("/")
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String viewHome(Model model) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }


        List<PaintingInfoDTO> myFavoritePaintings =
                paintingService.findAllMyFavoritePaintings(loggedUser.getId())
                        .stream()
                        .map(PaintingInfoDTO::new)
                        .toList();

        List<PaintingInfoDTO> myPaintings = paintingService.findAllMyPaintings(loggedUser.getId())
                .stream()
                .map(PaintingInfoDTO::new)
                .toList();

        List<PaintingInfoDTO> allOtherPaintings = paintingService.findAllOtherPaintings(loggedUser.getId())
                .stream()
                .map(PaintingInfoDTO::new)
                .toList();



        List<PaintingInfoDTO> topRated = paintingService.findMostRatedPaintings()
                .stream()
                .map(PaintingInfoDTO::new)
                .toList();

        model.addAttribute("myFavoritePaintings", myFavoritePaintings);
        model.addAttribute("myPaintings", myPaintings);
        model.addAttribute("allOtherPaintings", allOtherPaintings);
        model.addAttribute("topRated", topRated);


        return "home";


    }
}
