package com.example.demo;

import com.example.demo.service.Game;

public class Driver {
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.startGame();
    }
}
