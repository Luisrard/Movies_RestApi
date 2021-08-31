package com.luisrard.movies_api.models.dto;

import com.luisrard.movies_api.entities.enums.MovieRating;
import lombok.Data;

@Data
public class MovieGeneralInfo {
    private Integer id;
    private String name;
    private Float score;
    private String image;
    private Integer votes;
    private Integer duration;
    private MovieRating rating;
}
