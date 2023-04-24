package minesweeper;

import java.util.Scanner;

public class Command {
    private int numberRow, numberColumn;
    private String command;

    public void getUserInput(int col, int row) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter command"); // Output input

        String commandInput = scanner.nextLine();  // Read user input

        //Sorts the command into T or M and the column or row chars of the instance variables

        //T 5 6
        //M 7 2

        if (!validate(commandInput, col, row)) {
            System.out.println(commandInput + " is not a valid command.");
            getUserInput(col, row);
        }

        //erfolgreich!
    }

    public boolean validate(String str, int cols, int rows) {
        String[] commands = str.trim().split(" ");
        if (commands.length != 3) {
            return false;
        }

        command =  commands[0];
        try {
            numberColumn = Integer.parseInt(commands[1]);
            numberRow = Integer.parseInt(commands[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        if (numberColumn < 0 || numberColumn >= cols) {
            return false;
        }
        if (numberRow < 0 || numberRow >= rows) {
            return false;
        }
        if (!command.equalsIgnoreCase("T") && !command.equalsIgnoreCase("M")) {
            return false;
        }

        return true;
    }

    public int getNumberRow() {
        return this.numberRow;
    }

    public int getNumberColumn() {
        return this.numberColumn;
    }

    public String getCommand() {
        return command;
    }
}
