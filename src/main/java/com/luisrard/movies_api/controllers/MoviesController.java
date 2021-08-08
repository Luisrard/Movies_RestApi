package com.luisrard.movies_api.controllers;

import com.luisrard.movies_api.entities.Movie;
import com.luisrard.movies_api.services.MoviesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesController {
    private final MoviesService moviesService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(moviesService.getMovies());
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return ResponseEntity.ok(moviesService.addMovie(movie));
    }
}
