package battleship;

import java.io.IOException;
import java.util.Scanner;

class Printer {
    public static void printField(String[][] matrix) {
        // Helper function to print matrix
        char c = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (String[] upper : matrix) {
            System.out.printf("%c ", c++);
            for (String lower : upper) {
                System.out.print(lower + " ");
            }
            System.out.println();
        }
    }
}

class Coordinates {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    boolean isCoordValid = false;

    public void setCoordinates(String coordinates) {
        // Parse input to get correct coordinates
        coordinates = coordinates.toUpperCase();                    // Convert all input to uppercase to avoid input errors
        String[] splitCoordinates = coordinates.split(" ");
        this.x1 = translateRow(splitCoordinates[0].charAt(0));      // Get x coordinate
        splitCoordinates[0] = splitCoordinates[0].replaceAll("^[A-Z]+","");   // Remove letter and only keep the number
        this.y1 = Integer.parseInt(splitCoordinates[0]);            // Get y coordinate

        this.x2 = translateRow(splitCoordinates[1].charAt(0));      // Get x coordinate
        splitCoordinates[1] = splitCoordinates[1].replaceAll("^[A-Z]+","");   // Remove letter and only keep the number
        this.y2 = Integer.parseInt(splitCoordinates[1]);            // Get y coordinate

        // Indices start at 0, our input starts at 1. Decrement by one to get correct indices
        this.x1--;
        this.y1--;
        this.x2--;
        this.y2--;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public static int translateRow(char c) {
        return switch (c) {
            case 'A' -> 1;
            case 'B' -> 2;
            case 'C' -> 3;
            case 'D' -> 4;
            case 'E' -> 5;
            case 'F' -> 6;
            case 'G' -> 7;
            case 'H' -> 8;
            case 'I' -> 9;
            case 'J' -> 10;
            default -> -1;
        };
    }

    public void getCoordinates(String input, Coordinates obj) {
        // Get all values from one function. Prevents calling a getter for each field
        obj.setCoordinates(input);
        y1 = obj.getY1();
        y2 = obj.getY2();
        x1 = obj.getX1();
        x2 = obj.getX2();
    }

    public boolean isCoordinateValid(Coordinates obj, String input, Scanner scanner, String shipType, int lim, String[][] field) {
        // Check for diagonal
        while (y1 != y2 && x1 != x2) {
            System.out.println("Error! Wrong ship location! Try again:\n");
            input = scanner.nextLine();
            this.getCoordinates(input, obj);
            System.out.println();
        }

        // Check for closeness
        while (
                   (x1 + 1 < 10 && field[x1 + 1][y1] == "O") || (x1 - 1 > 0 && field[x1 - 1][y1] == "O")
                || (x2 + 1 < 10 && field[x2 + 1][y1] == "O") || (x2 - 1 > 0 && field[x2 - 1][y1] == "O")
                || (x1 + 1 < 10 && field[x1 + 1][y2] == "O") || (x1 - 1 > 0 && field[x1 - 1][y2] == "O")
                || (x2 + 1 < 10 && field[x2 + 1][y2] == "O") || (x2 - 1 > 0 && field[x2 - 1][y2] == "O")
        ) {
            System.out.println("Error! You placed it too close to another one. Try again:\n");
            input = scanner.nextLine();
            getCoordinates(input, obj);
            System.out.println();
        }

        // Check for length
        while(true) {
            if (x1 == x2 && Math.abs(y2-y1) != lim) {
                System.out.printf("Error! Wrong length of the %s! Try again:\n\n", shipType);
                input = scanner.nextLine();
                getCoordinates(input, obj);
                System.out.println();
            } else if (y1 == y2 && Math.abs(x2-x1) != lim) {
                System.out.printf("Error! Wrong length of the %s! Try again:\n\n", shipType);
                input = scanner.nextLine();
                getCoordinates(input, obj);
                System.out.println();
            } else {
                break;
            }
        }

        return true;
    }
    public void getInput(String[][] field, String first, String second, int lim, Coordinates obj, Field fObj, String[][] copyField) {
        Scanner scanner = new Scanner(System.in);
        String input = null;

        System.out.printf("\nEnter the coordinates of the %s:\n\n", first);

        input = scanner.nextLine();
        obj.getCoordinates(input, obj);
        System.out.println();

        while(!obj.isCoordValid) {
            obj.isCoordValid = isCoordinateValid(obj, input, scanner, second, lim, field);
        }
        obj.isCoordValid = false;

        fObj.placeShip(field, x1, y1, x2, y2, copyField, second);
        Printer.printField(field);
    }
}

class Field {
    private final int size = 10;        // Size is always 10x10 so this can be constant
    public String[][] createField() {
        // Create an empty field
        String[][] field = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = "~";
            }
        }
        return field;
    }

    private void storeInCopy(String shipType, String[][] copyField, int row, int col) {
        switch (shipType) {
            case "Aircraft Carrier":
                copyField[row][col] = "A";
                break;
            case "Battleship":
                copyField[row][col] = "B";
                break;
            case "Submarine":
                copyField[row][col] = "S";
                break;
            case "Cruiser":
                copyField[row][col] = "C";
                break;
            default:
                copyField[row][col] = "D";
                break;
        }
    }
    public int placeShip(String[][] field, int x1, int y1, int x2, int y2, String[][] copyField, String shipType) {
        for (int col = y1; col <= y2; col++) {
            field[x1][col] = "O";
            storeInCopy(shipType, copyField, x1, col);
        }

        for (int col = y2; col <= y1; col++) {
            field[x2][col] = "O";
            storeInCopy(shipType, copyField, x2, col);
        }

        for (int row = x1; row <= x2; row++) {
            field[row][y1] = "O";
            storeInCopy(shipType, copyField, row, y1);
        }

        for (int row = x2; row <= x1; row++) {
            field[row][y2] = "O";
            storeInCopy(shipType, copyField, row, y2);
        }

        return 0;
    }

}

public class Main {

    public static void cls() {
        for(int clear = 0; clear < 50; clear++)
        {
            System.out.println("\b") ;
        }
    }
    private String[][] initializeFields(String[][] copyField) {
        Coordinates obj = new Coordinates();
        Field fObj = new Field();
        String[][] field = fObj.createField();

        Printer.printField(field);

        obj.getInput(field, "Aircraft Carrier (5 cells)", "Aircraft Carrier", 4, obj, fObj, copyField);
        obj.getInput(field, "Battleship (4 cells)", "Battleship", 3, obj, fObj, copyField);
        obj.getInput(field, "Submarine (3 cells)", "Submarine", 2, obj, fObj, copyField);
        obj.getInput(field, "Cruiser (2 cells)", "Cruiser", 2, obj, fObj, copyField);
        obj.getInput(field, "Destroyer (2 cells)", "Destroyer", 1, obj, fObj, copyField);

        return field;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Main obj = new Main();
        Field fObj = new Field();

        final String exchangeMessage = "\nPress Enter and pass the move to another player\n...";

        // Player 1
        System.out.println("Player 1, place your ships on the game field\n");
        String[][] player1CopyField = fObj.createField();
        String[][] player1Field1 = obj.initializeFields(player1CopyField);
        String[][] player1Field2 = fObj.createField();

        //Player 2
        System.out.println(exchangeMessage);
        scanner.nextLine();

        System.out.println("Player 2, place your ships to the game field\n");
        String[][] player2CopyField = fObj.createField();
        String[][] player2Field1 = obj.initializeFields(player2CopyField);
        String[][] player2Field2 = fObj.createField();

        System.out.println(exchangeMessage);
        scanner.nextLine();

        Gameplay gp = new Gameplay();


        gp.attack(player1Field1, player1CopyField, player2Field1, player2CopyField, player1Field2, gp);


    }
}
