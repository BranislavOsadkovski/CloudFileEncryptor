package com.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class User {
    @JsonProperty
    private int id;
    @JsonProperty
    private String message;
    @JsonProperty
    private String name;

    public User(int id,  String message, String name) {
        this.id = id;
        this.message = message;
        this.name = name;
    }
}
