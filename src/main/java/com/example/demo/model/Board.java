package com.example.demo.model;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Board {
    private Integer size;
    private Cell[][] board;

    public Board(Integer size) {
        this.size = size;
        initCell(size);
    }

    public boolean addSnakeOrLadders(Jump jump, Integer position) {
        Cell cell = getCellById(position);
        if (null == cell.getJump()) {
            cell.setJump(jump);
            return true;
        }
        else {
            return false;
        }
    }

    public Cell getCellById(Integer id) {
        id --;
        int row = id / size;
        int column = id % size;
        return this.board[row][column];
    }

    private void initCell(Integer size) {
        this.board = new Cell[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                board[i][j] = new Cell();
            }
        }
    }
}
