package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.enums.StyleName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPaintingDTO {

    @NotNull
    @Size(min = 5, max = 40,message = "Name length must be between 5 and 40 characters!")
    private String name;

    @NotNull
    @Size(min = 5, max = 30,message = "Author length must be between 5 and 30 characters!")
    private String author;

    @NotNull(message = "Please enter valid image URL!")
    private String imageURL;

    @NotNull(message = "You must select a style!")
    private StyleName styleName;

    public AddPaintingDTO() {
    }

    public String getName() {
        return name;
    }

    public AddPaintingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public AddPaintingDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public AddPaintingDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public AddPaintingDTO setStyleName(StyleName styleName) {
        this.styleName = styleName;
        return this;
    }
}
