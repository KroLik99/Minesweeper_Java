package minesweeper;

public class Board {
    private BackgroundBoard backgroundBoard;
    private int col;
    private int row;
    private int score = 0;
    private Command command = new Command();

    private boolean isRunning;

    public Board(int colmns, int rows) {
        this.col = colmns;
        this.row = rows;
        backgroundBoard = new BackgroundBoard(colmns, rows);
    }

    public void start() {
        backgroundBoard.createBackgroundBoard();

        //TODO Mitteilung Spielstart
        System.out.println("The bombs are hidden and the game started. Please enter a command in the following format:");
        System.out.println("T x y where x and y are whole numbers that signify the coordinates on the board that should be turned.");
        System.out.println("M x y where x and y are whole numbers that signify the coordinates on the board that should be marked as a suspected bomb.");
        isRunning = true;
        while (isRunning) {
            printGameBoard();command.getUserInput(col, row);
            executeCommand();
        }
        printGameBoard();
    }


    public void executeCommand() {
        // read command, update board, game over
        Cell cell = backgroundBoard.getCell(command.getNumberColumn(), command.getNumberRow());
        int columnNumberCell = this.command.getNumberColumn();
        int rowNumberCell = this.command.getNumberColumn();
        if ("T".equalsIgnoreCase(command.getCommand())) { // test (turn around)

            cell.setDisabled(true);

            if (cell.isBomb()) {
                gameOver();
            } else {

                if (cell.getNumber() == 0) {
                    //TODO alles aufdecken
                    for(int column = columnNumberCell + 1; column >= columnNumberCell - 1; column--) {
                        for (int row = rowNumberCell + 1; row >= rowNumberCell - 1; row--) {

                            Cell otherCell = backgroundBoard.getCell(column,row);
                            if (otherCell.getNumber() == 1) {
                                otherCell.setDisabled(true);
                            }
                        }
                    }
                }

                score++;
                checkGameStatus();
            }
        } else if ("M".equalsIgnoreCase(command.getCommand())) { //mark as a bomb
            cell.setFlag(true);
            score++;
            checkGameStatus();
        } else {
            System.out.println("Error in given command.");
        }

    }

    public void checkGameStatus() {
        //Checks if gameWin
        if (score == col * row) {
            gameWin();
        }
    }

    public void gameOver() {
        //When a bomb has been turned around the game ends
        System.out.println("You lost!");
        isRunning = false;
    }

    public void gameWin() {
        //When everything has been turned around the game ends
        System.out.println("You won!");
        isRunning = false;
    }

    public void printGameBoard() {
        //Prints the array of the board to the console using a for loop
        System.out.println();
        System.out.println();

        System.out.print("    ");
        for (int boardDimensionCol = 0; boardDimensionCol <= this.col - 1; boardDimensionCol++) {
            System.out.print(boardDimensionCol + "  ");
        }
        System.out.println();
        for (int boardDimensionCol = 0; boardDimensionCol < this.col; boardDimensionCol++) {
            System.out.print(boardDimensionCol + "   "); // left sidebar of the board
            for (int boardDimensionRow = 0; boardDimensionRow < row; boardDimensionRow++) {
                Cell cell = backgroundBoard.getCell(boardDimensionCol, boardDimensionRow);
                System.out.print(mapToString(cell) + "  "); // Add the values
                if (boardDimensionRow == 7) {
                    System.out.println(" ");
                }
            }
        }

        System.out.println();
    }

    private String mapToString(Cell cell) {
        if (cell.isFlag()) {
            return "!";
        }

        if (!cell.isDisabled()) {
            return " ";
        }

        if (cell.isBomb()) {
            return "*";
        }

        return "" + cell.getNumber(); //tostring funktioniert nicht auf primitiven Datentypen int (nur Integer)
    }

}

