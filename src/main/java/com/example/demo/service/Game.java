package com.example.demo.service;

import com.example.demo.model.Board;
import com.example.demo.model.Cell;
import com.example.demo.model.Dice;
import com.example.demo.model.Player;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

public class Game {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> players;

    public Game(int size, int diceCount) {
        this.board = new Board(size);
        this.dice = new Dice(diceCount);
        players = new LinkedList<>();
    }

    public void addSnake(Integer start, Integer end) {
        this.board.addSnake(start, end);
    }

    public void addLadder(Integer start, Integer end) {
        this.board.addLadder(start, end);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
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
        } else {
            while (!Objects.equals(finalPosition, board.getCellById(finalPosition).getMove().getNextPosition())) {
                Cell intermediateCell = board.getCellById(finalPosition);
                System.out.println("Jump done by: " + intermediateCell.getMove().getType());
                finalPosition = intermediateCell.getMove().getNextPosition();
            }
        }
        System.out.println("Player : " + player.getName() + " moved to : " + finalPosition);
        return finalPosition;
    }

}
