package com.luisrard.movies_api.controllers;

import com.luisrard.movies_api.entities.Movie;
import com.luisrard.movies_api.services.MoviesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MoviesController extends ControllerApi<Movie, Object, MoviesService> {

    public MoviesController(MoviesService service) {
        super(MoviesController.class, service);

    }

    @GetMapping("/find")
    @Override
    public ResponseEntity<Movie> findObject(int id) {
        return super.findObject(id);
    }



    @PostMapping
    @Override
    public ResponseEntity<Movie> saveObject(Movie requestObject) {
        return super.saveObject(requestObject);
    }

    @PutMapping
    @Override
    public ResponseEntity<Movie> updateObject(Movie requestObject) {
        return super.updateObject(requestObject);
    }

    @DeleteMapping
    @Override
    public ResponseEntity<?> deleteObject(int id) {
        return super.deleteObject(id);
    }

    @Override
    protected String getEntityName() {
        return "Movie";
    }

    @Override
    protected String getEntityPluralName() {
        return "Movies";
    }
}
