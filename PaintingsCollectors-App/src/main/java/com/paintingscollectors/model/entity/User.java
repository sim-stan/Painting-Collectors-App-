package com.paintingscollectors.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20)
    private String username;
    @NotNull
    private String password;
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER)
    private List<Painting> myPaintings;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Painting> favoritePaintings;

    @ManyToMany
    private List<Painting> ratedPaintings;

    public User() {

        this.myPaintings=new ArrayList<>();
        this.favoritePaintings=new ArrayList<>();
        this.ratedPaintings=new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Painting> getMyPaintings() {
        return myPaintings;
    }

    public User setMyPaintings(List<Painting> myPaintings) {
        this.myPaintings = myPaintings;
        return this;
    }

    public List<Painting> getFavoritePaintings() {
        return favoritePaintings;
    }

    public User setFavoritePaintings(List<Painting> favoritePaintings) {
        this.favoritePaintings = favoritePaintings;
        return this;
    }

    public List<Painting> getRatedPaintings() {
        return ratedPaintings;
    }

    public User setRatedPaintings(List<Painting> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
        return this;
    }

    public void addFavourite(Painting painting) {
        this.favoritePaintings.add(painting);
        painting.setFavorite(true);
    }


}
