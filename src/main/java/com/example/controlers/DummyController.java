package com.example.controlers;

import com.example.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dummy")
public class DummyController {

    private final Person person;

    @Autowired
    public DummyController(Person person) {
        this.person = person;
    }

    @GetMapping
    public String save(){
        System.out.println(person.find());
        return person.find().toString();
    }
}
