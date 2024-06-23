package com.paintingscollectors.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "paintings")
public class Painting {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 40)
    private String name;

    @NotNull
    @Size(min = 5, max = 30)
    private String author;

    @NotNull
    @ManyToOne
    private Style style;

    @NotNull
    @ManyToOne
    private User owner;
    @NotNull
    private String imageURL;
    @NotNull
    private boolean isFavorite;
    @NotNull
    private int votesCount;


    public Painting() {
    }

    public Painting(int i) {


    }

    public Long getId() {
        return id;
    }

    public Painting setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Painting setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Painting setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Painting setStyle(Style style) {
        this.style = style;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Painting setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Painting setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public Painting setFavorite(boolean favorite) {
        isFavorite = favorite;
        return this;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public Painting setVotesCount(int hasVotes) {
        this.votesCount = hasVotes;
        return this;
    }

    public void addVote(){
        this.votesCount++;
    }
}
