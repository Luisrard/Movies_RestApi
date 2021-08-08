package com.luisrard.movies_api.services;

import com.luisrard.movies_api.entities.Movie;
import com.luisrard.movies_api.repositories.MoviesRepo;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MoviesService implements ServiceApi<Movie, Object>{
    private final MoviesRepo moviesRepo;

    @Override
    public List<Movie> getObjects() {
        return moviesRepo.findAll();
    }

    @Override
    public Movie find(@NonNull Integer id) {
        if(id != null)
            return moviesRepo.findById(id).orElse(null);
        return null;
    }

    @Override
    public Movie save(@NonNull Movie request) throws RuntimeException {
        request.setId(null);
        return moviesRepo.save(request);
    }


    @Override
    public Movie update(@NonNull Movie request) throws RuntimeException {
        return moviesRepo.save(request);
    }

    @Override
    public boolean delete(@NonNull Integer id) throws RuntimeException {
        moviesRepo.deleteById(id);
        return true;
    }
}
