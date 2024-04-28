package com.example.demo.model;

import com.example.demo.enums.MoveType;
import lombok.Getter;

@Getter
public class Move {
    private final Integer nextPosition;
    private final MoveType type;

    public Move(Integer nextPosition, MoveType moveType) {
        this.nextPosition = nextPosition;
        this.type = moveType;
    }
}
