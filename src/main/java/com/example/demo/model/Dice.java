package com.example.demo.model;


import lombok.Getter;

import java.util.Random;

@Getter
public class Dice {

    private final Integer count;

    public Dice(Integer count) {
        this.count = count;
    }

    public Integer roll() {
        return ((Math.abs(new Random().nextInt()) % 6) + 1) * count;
    }
}
