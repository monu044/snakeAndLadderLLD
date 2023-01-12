package com.example.demo.model;


import lombok.*;

import java.util.Random;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dice {

    private Integer count;

    public Integer roll() {
        return ((Math.abs(new Random().nextInt()) % 6) + 1) * count;
    }
}
