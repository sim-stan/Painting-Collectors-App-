package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.model.enums.StyleName;

public class PaintingInfoDTO {

    private long id;
    private String name;
    private String author;
    private StyleName styleName;
    private String imageURL;
    private User owner;

    private int votesCount;

    public PaintingInfoDTO(Painting painting) {
        this.id = painting.getId();
        this.name = painting.getName();
        this.author = painting.getAuthor();
        this.styleName = painting.getStyle().getStyleName();
        this.imageURL = painting.getImageURL();
        this.owner = painting.getOwner();


    }

    public long getId() {
        return id;
    }

    public PaintingInfoDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PaintingInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PaintingInfoDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public PaintingInfoDTO setStyleName(StyleName styleName) {
        this.styleName = styleName;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public PaintingInfoDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public PaintingInfoDTO setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public PaintingInfoDTO setVotesCount(int votesCount) {
        this.votesCount = votesCount;
        return this;
    }
}
