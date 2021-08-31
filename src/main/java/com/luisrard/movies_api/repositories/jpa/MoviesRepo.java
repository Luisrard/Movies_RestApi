package com.luisrard.movies_api.repositories.jpa;

import com.luisrard.movies_api.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepo extends JpaRepository<Movie, Integer> {
}
