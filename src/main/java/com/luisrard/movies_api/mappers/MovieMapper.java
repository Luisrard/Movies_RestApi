package com.luisrard.movies_api.mappers;

import com.luisrard.movies_api.entities.Movie;
import com.luisrard.movies_api.models.dto.MovieGeneralInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieGeneralInfo movieToMovieGeneralInfo(Movie movie);
    List<MovieGeneralInfo> movieToGeneralInfo(List<Movie> movies);
}
