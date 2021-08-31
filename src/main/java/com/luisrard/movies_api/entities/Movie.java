package com.luisrard.movies_api.entities;

import com.luisrard.movies_api.entities.enums.MovieRating;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 60, unique = true)
    private String name;
    @Column(nullable = false, length = 400)
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
    @ManyToOne
    @JoinColumn(name = "director",nullable = false)
    private Person director;
}
