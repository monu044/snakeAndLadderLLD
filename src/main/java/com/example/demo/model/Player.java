package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class Player {
    private final String id;
    private final String name;
    @Setter
    private Integer currentPosition;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.currentPosition = 1;
    }
}
