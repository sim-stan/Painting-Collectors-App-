package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.enums.StyleName;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    @Enumerated(EnumType.STRING)
    private StyleName styleName;

    private String description;

    @OneToMany(mappedBy = "style")
    private List<Painting> paintings;

    public Style() {
        this.paintings=new ArrayList<>();
    }

    public Style(StyleName styleName, String description) {
        super();
        this.styleName = styleName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Style setId(Long id) {
        this.id = id;
        return this;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public Style setStyleName(StyleName styleName) {
        this.styleName = styleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    public Style setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
        return this;
    }
}
