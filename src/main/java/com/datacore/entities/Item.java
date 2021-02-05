package com.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty
    private final int id;
    @JsonProperty
    private final String name;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
