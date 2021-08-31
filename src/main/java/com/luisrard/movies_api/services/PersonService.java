package com.luisrard.movies_api.services;

import com.luisrard.movies_api.entities.Person;
import com.luisrard.movies_api.repositories.jpa.PersonRepo;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService implements ServiceApi<Person, Object>{
    private final PersonRepo personRepo;

    @Override
    public Person find(@NonNull Integer id) {
        return personRepo.findById(id).orElse(null);
    }

    @Override
    public Person save(@NonNull Person request) throws RuntimeException {
        request.setId(null);
        return personRepo.save(request);
    }

    @Override
    public Person update(@NonNull Person request) throws RuntimeException {
        return personRepo.save(request);
    }

    @Override
    public boolean delete(@NonNull Integer id) throws RuntimeException {
        personRepo.deleteById(id);
        return true;
    }
}
