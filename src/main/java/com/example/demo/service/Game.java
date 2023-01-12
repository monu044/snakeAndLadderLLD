package com.example.demo.service;

import com.example.demo.enums.JumpType;
import com.example.demo.model.*;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;

@NoArgsConstructor
public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;

    public void initialize() {
        this.board = new Board(10);
        this.dice = new Dice(1);
        this.players = new LinkedList<>();

        Player player1 = Player.builder()
                .id("Player1")
                .name("Ram")
                .currentPosition(1)
                .build();

        Player player2 = Player.builder()
                .id("Player2")
                .name("Ramesh")
                .currentPosition(1)
                .build();

        this.players.add(player1);
        this.players.add(player2);

        // addSnake
        for (int i = 0; i < 5; ++i) {
            int start = Math.abs(new Random().nextInt()) % (this.board.getSize() * this.board.getSize());
            int end = Math.abs(new Random().nextInt()) % (this.board.getSize() * this.board.getSize());
            if (Jump.validDate(start, end, JumpType.SNAKE) && start != 100) {
                board.addSnakeOrLadders(new Jump(start, end, JumpType.SNAKE), start);
            }
        }

        //addLadder
        for (int i = 0; i < 5; ++i) {
            int start = Math.abs(new Random().nextInt()) % (this.board.getSize() * this.board.getSize());
            int end = Math.abs(new Random().nextInt()) % (this.board.getSize() * this.board.getSize());
            if (Jump.validDate(start, end, JumpType.LADDER) && start != 100) {
                board.addSnakeOrLadders(new Jump(start, end, JumpType.LADDER), start);
            }
        }
    }

    public void startGame() {
        Player winner = null;
        do {
            Player currentPlayer = players.poll();
            Integer number = dice.roll();
            assert currentPlayer != null;
            currentPlayer.setCurrentPosition(move(currentPlayer, number));
            players.add(currentPlayer);
        } while (check().isEmpty());

        winner = check().get();
        System.out.println("Winner is " + winner.getName());
    }

    private Optional<Player> check() {
        return players.stream()
                .filter(player -> player.getCurrentPosition().equals(board.getSize() * board.getSize()))
                .findAny();
    }

    private Integer move(Player player, Integer number) {
        System.out.println("Player : " + player.getName() + " rolled the dice and got : " + number);
        Integer currentPos = player.getCurrentPosition();
        Integer finalPosition = currentPos + number;
        if (finalPosition >= board.getSize() * board.getSize()) {
            finalPosition = board.getSize() * board.getSize();
        }
        else {
            while (null != board.getCellById(finalPosition).getJump()) {
                Cell intermediateCell = board.getCellById(finalPosition);
                System.out.println("Jump done by: " + intermediateCell.getJump().getType());
                finalPosition = intermediateCell.getJump().getEnd();
            }
        }
        System.out.println("Player : " + player.getName() + " moved to : " + finalPosition);
        return finalPosition;
    }

}
