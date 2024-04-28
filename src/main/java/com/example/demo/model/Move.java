package com.example.demo.model;

import lombok.Getter;

@Getter
public class Move {
    private final Integer nextPosition;
    private final String type;

    public Move(Integer nextPosition, String type) {
        this.nextPosition = nextPosition;
        this.type = type;
    }
}
