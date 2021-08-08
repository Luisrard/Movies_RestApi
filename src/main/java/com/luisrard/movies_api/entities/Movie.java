package com.luisrard.movies_api.entities;

import com.luisrard.movies_api.entities.enums.MovieRating;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false)
    private String resume;
    private Float score;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private Integer votes = 0;
    @Column(nullable = false)
    private Integer duration;
    @Enumerated(EnumType.STRING)
    private MovieRating rating;
}
