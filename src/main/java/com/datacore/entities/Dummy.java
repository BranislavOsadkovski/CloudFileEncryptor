package com.example.entities;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
public class Dummy {

//    public static final String TABLE_NAME = "dummy";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public Dummy() {
    }
    public Dummy(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dummy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
