package com.luisrard.movies_api.repositories.jpa;

import com.luisrard.movies_api.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Integer> {
}
