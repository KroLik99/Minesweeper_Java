package minesweeper;

import java.util.Random;
/*
Board that keeps track of the solutions (bomb placement and numbers)
 */

public class BackgroundBoard {
    private int dimensionCol;
    private int dimensionRow;
    private Cell[][] board;

    public BackgroundBoard(int colmns, int rows) {
        this.dimensionCol = colmns;
        this.dimensionRow = rows;
        board = new Cell[dimensionCol][dimensionRow];

        for (int column = 0; column < dimensionCol; column++) {
            for (int row = 0; row < dimensionRow; row++) {
                board[column][row] = new Cell();
            }
        }
    }

    public void createBackgroundBoard() {
        setBombs();
    }

    public Cell getCell(int column, int row) {
        return board[column][row];
    }

    private void setBombs() {
        Random random = new Random();
        for (int i = 0; i < dimensionCol; i++) {
            int row = random.nextInt(this.dimensionCol); //Get a random nr between 0 and 8
            int col = random.nextInt(this.dimensionRow); //Get a random nr between 0 and 8
            board[col][row].setBomb(true); //set to bomb
            setNumbers(col, row);
        }
    }

    private void setNumbers(int bombColumn, int bombRow) {
        for (int column = bombColumn + 1; column >= bombColumn - 1; column--) {
            for (int row = bombRow + 1; row >= bombRow - 1; row--) {
                if (!(column == bombColumn && row == bombRow)) {
                    if (isValidBoardPosition(column, row)) {
                        board[column][row].setNumber(board[column][row].getNumber() + 1);
                        //-> board[currentX][currentY].number += 1;
                    }
                }
            }
        }
    }

    private boolean isValidBoardPosition(int column, int row) {
        return column >= 0 && column < dimensionCol && row >= 0 && row < dimensionRow;
    }

}
