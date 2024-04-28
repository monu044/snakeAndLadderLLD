package com.example.demo;

import com.example.demo.model.Player;
import com.example.demo.service.Game;

import java.util.Random;

public class Driver {
    public static void main(String[] args) {
        Game game = new Game(10, 1);
        int size = 10;
        // addSnake
        for (int i = 0; i < 5; ++i) {
            int start = Math.abs(new Random().nextInt()) % (size * size) + 1;
            int end = Math.abs(new Random().nextInt()) % (size * size) + 1;
            if (start < end) {
                game.addSnake(end, start);
            } else {
                game.addSnake(start, end);
            }
        }

        //addLadder
        for (int i = 0; i < 5; ++i) {
            int start = Math.abs(new Random().nextInt()) % (size * size) + 1;
            int end = Math.abs(new Random().nextInt()) % (size * size) + 1;
            if (start > end) {
                game.addLadder(end, start);
            } else {
                game.addLadder(start, end);
            }
        }
        game.addPlayer(new Player("1", "RAM"));
        game.addPlayer(new Player("2", "RAKESH"));


        game.startGame();
    }
}
