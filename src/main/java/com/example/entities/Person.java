package com.example.entities;

import com.example.DAO.DummyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Person {

    private DummyDAO dummyDAO;

    @Autowired
    public Person(DummyDAO dummyDAO){
        this.dummyDAO=dummyDAO;
    }

    public String find(){
        List<Dummy> l = dummyDAO.findByName("banex");
      return l.stream().findFirst().toString();
    }
}
