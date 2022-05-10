package com.lorenchess.movie_library.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private String releaseYear;

    @Column(nullable = false)
    private String rating;


    private String comments;

    private String image;

    public Movie(String title, String director, String releaseYear, String rating, String comments, String image) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.comments = comments;
        this.image = image;
    }

    @Transient
    public String getImagePath(){
        if (image == null) return null;

        return "/movie-image/" + id + "/" + image;
    }
}
