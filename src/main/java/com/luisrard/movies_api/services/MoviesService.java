package com.luisrard.movies_api.services;

import com.luisrard.movies_api.entities.Movie;
import com.luisrard.movies_api.repositories.MoviesRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MoviesService {
    private final MoviesRepo moviesRepo;

    public List<Movie> getMovies() {
        return moviesRepo.findAll();
    }

    public Movie addMovie(Movie movie){
        movie.setId(null);
        return moviesRepo.save(movie);
    }
}
