package com.luisrard.movies_api.models.search_criteria;

import com.luisrard.movies_api.entities.Movie;
import com.luisrard.movies_api.entities.enums.MovieRating;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Filter of {@link Movie} as the following:<br>
 */
@Data
@NoArgsConstructor
public class MovieSearchCriteria {
    private String name;
    private MovieRating rating;
}
