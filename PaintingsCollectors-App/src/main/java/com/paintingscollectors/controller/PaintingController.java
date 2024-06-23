package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PaintingController {


    private final PaintingService paintingService;
    private final LoggedUser loggedUser;

    public PaintingController(PaintingService paintingService, LoggedUser loggedUser) {
        this.paintingService = paintingService;
        this.loggedUser = loggedUser;
    }


    @ModelAttribute("addPaintingDTO")
    public AddPaintingDTO addPaintingDTO() {

        return new AddPaintingDTO();
    }

    @GetMapping("/paintings/add")
    public String addPainting() {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        return "add-painting";
    }

    @PostMapping("/paintings/add")
    public String addPainting(@Valid AddPaintingDTO addPaintingDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPaintingDTO", addPaintingDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addPaintingDTO", bindingResult);

            return "redirect:/paintings/add";
        }

        boolean success = paintingService.addPainting(addPaintingDTO);

        if (!success) {
            redirectAttributes.addFlashAttribute("hasAddPaintingErrors", addPaintingDTO);
            return "redirect:/paintings/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/paintings/add-to-favourites/{paintingId}")
    public String addToFavourites(@PathVariable long paintingId) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        paintingService.addToFavourites(loggedUser.getId(), paintingId);

        return "redirect:/home";
    }



     @GetMapping("/paintings/remove/{paintingId}")
    public String removeFromMyPainting(@PathVariable long paintingId) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

      paintingService.deletePainting(loggedUser.getId(), paintingId);

        return "redirect:/home";
    }


    @GetMapping("/paintings/remove-from-favorite/{paintingId}")
    public String removeFromMyFavoritePainting (@PathVariable long paintingId) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        paintingService.deletePaintingFromFavorite(loggedUser.getId(),paintingId);

        return "redirect:/home";
    }


    @GetMapping("/paintings/vote-for-painting/{id}")
    public String voteForPainting(@PathVariable long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

      paintingService.voteForPainting(id);

        return "redirect:/home";
    }





}
