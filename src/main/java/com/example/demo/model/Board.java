package com.example.demo.model;

import com.example.demo.enums.MoveType;
import com.example.demo.exception.InvalidCellException;
import com.example.demo.exception.InvalidMoveException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Board {
    @Getter
    private final Integer size;
    private final List<Cell> board;

    public Board(Integer size) {
        this.size = size;
        board = new ArrayList<>();
        initCell(size);
    }

    public boolean addSnake(Integer startPosition, Integer endPosition) throws InvalidCellException {
        if (startPosition < endPosition) {
            throw new InvalidMoveException("Snake should move the player down");
        } else {
            Cell startingCell = getCellById(startPosition);
            Cell endingCell = getCellById(endPosition);
            if (Objects.equals(startingCell.getMove().getNextPosition(), startPosition)) {
                startingCell.setMove(new Move(endPosition, MoveType.SNAKE) {
                });
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean addLadder(Integer startPosition, Integer endPosition) throws InvalidCellException {
        if (startPosition > endPosition) {
            throw new InvalidMoveException("Ladder should move the player up");
        } else {
            Cell startingCell = getCellById(startPosition);
            Cell endingCell = getCellById(endPosition);
            if (Objects.equals(startingCell.getMove().getNextPosition(), startPosition)) {
                startingCell.setMove(new Move(endPosition, MoveType.LADDER));
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean checkCellExists(Integer id) {
        id--;
        return id >= 0 && id < board.size();
    }

    public Cell getCellById(Integer id) throws InvalidCellException {
        if (!checkCellExists(id)) {
            throw new InvalidCellException("No cell exists with ID : " + id);
        } else {
            id--;
            return board.get(id);
        }
    }

    private void initCell(Integer size) {
        for (int i = 0; i < size * size; i++) {
            board.add(new Cell(i + 1, new Move(i + 1, MoveType.DEFAULT)));
        }
    }
}
