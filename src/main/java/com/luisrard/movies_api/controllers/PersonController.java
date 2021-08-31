package com.luisrard.movies_api.controllers;

import com.luisrard.movies_api.entities.Person;
import com.luisrard.movies_api.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PersonController extends ControllerApi<Person, Object, PersonService>{
    public PersonController(PersonService service) {
        super(PersonController.class, service);
    }

    @GetMapping("/find")
    @Override
    public ResponseEntity<Person> findObject(Integer id) {
        return super.findObject(id);
    }

    @PostMapping
    @Override
    public ResponseEntity<Person> saveObject(Person requestObject) {
        return super.saveObject(requestObject);
    }

    @PutMapping
    @Override
    public ResponseEntity<Person> updateObject(Person requestObject) {
        return super.updateObject(requestObject);
    }

    @DeleteMapping
    @Override
    public ResponseEntity<Object> deleteObject(int id) {
        return super.deleteObject(id);
    }

    @Override
    protected String getEntityName() {
        return "Person";
    }

    @Override
    protected String getEntityPluralName() {
        return "People";
    }
}
