package battleship;

import java.util.Scanner;

public class Gameplay {
    private int x;
    private int y;

    private int p1aircraft_carriers = 5;
    private int p1battleships = 4;
    private int p1submarines = 3;
    private int p1cruisers = 2;
    private int p1destroyers = 2;

    private int p2aircraft_carriers = 5;
    private int p2battleships = 4;
    private int p2submarines = 3;
    private int p2cruisers = 2;
    private int p2destroyers = 2;

    public void getCoordinates(String point) {
        Coordinates coord = new Coordinates();
        point = point.toUpperCase();
        x = coord.translateRow(point.charAt(0));
        point = point.replaceAll("^[A-Z]+","");
        y = Integer.parseInt(point);

        x--;
        y--;

    }

    public void modifyField(String[][] originalField, String[][] copyField, int player) {
        if (copyField[x][y] == "X") {
            System.out.println("You hit a ship!");
        } else if (copyField[x][y] == "M") {
            System.out.println("You missed!");
        } else if (copyField[x][y] == "A") {
            if (player == 1) {
                p1aircraft_carriers--;
            } else {
                p2aircraft_carriers--;
            }
            copyField[x][y] = "X";
            originalField[x][y] = "X";
            System.out.println("You hit a ship! Try again:");
        } else if (copyField[x][y] == "B") {
            if (player == 1) {
                p1battleships--;
            } else {
                p2battleships--;
            }
            copyField[x][y] = "X";
            originalField[x][y] = "X";
            System.out.println("You hit a ship! Try again:");
        } else if (copyField[x][y] == "S") {
            if (player == 1) {
                p1submarines--;
            } else {
                p2submarines--;
            }
            copyField[x][y] = "X";
            originalField[x][y] = "X";
            System.out.println("You hit a ship! Try again:");
        } else if (copyField[x][y] == "C") {
            if (player == 1) {
                p1cruisers--;
            } else {
                p2cruisers--;
            }
            copyField[x][y] = "X";
            originalField[x][y] = "X";
            System.out.println("You hit a ship! Try again:");
        } else if (copyField[x][y] == "D") {
            if (player == 1) {
                p1destroyers--;
            } else {
                p2destroyers--;
            }
            copyField[x][y] = "X";
            originalField[x][y] = "X";
            System.out.println("You hit a ship! Try again:");
        } else {
            copyField[x][y] = "M";
            originalField[x][y] = "M";
            System.out.println("You missed!");
        }

        if (p1aircraft_carriers == 0 || p1battleships == 0 || p1submarines == 0 || p1cruisers == 0 || p1destroyers == 0
                || p2aircraft_carriers == 0 || p2battleships == 0 || p2submarines == 0 || p2cruisers == 0 || p2destroyers == 0)
        {
            System.out.println("You sank a ship!");
        }
    }

    public void attack(String[][] field, String[][] field2, String[][] opField, String[][] opField2, String[][] empty, Gameplay gp) {
        Scanner scanner = new Scanner(System.in);
        int player_counter = 0;
        while ((p1aircraft_carriers > 0 || p1battleships > 0 || p1submarines > 0 || p1cruisers > 0 || p1destroyers > 0) &&
        (p2aircraft_carriers > 0 || p2battleships > 0 || p2submarines > 0 || p2cruisers > 0 || p2destroyers > 0)) {
            String attackPoint = "";
            if (player_counter % 2 == 0) {
                while (attackPoint == "") {
                    Printer.printField(empty);
                    System.out.println("---------------------");
                    Printer.printField(field);
                    System.out.println("\nPlayer 1, it's your turn:");
                    attackPoint = scanner.nextLine();
                }
                gp.getCoordinates(attackPoint);
                modifyField(opField, opField2, 1);
            } else {
                while (attackPoint == "") {
                    Printer.printField(empty);
                    System.out.println("---------------------");
                    Printer.printField(opField);
                    System.out.println("\nPlayer 2, it's your turn:");
                    attackPoint = scanner.nextLine();
                }
                gp.getCoordinates(attackPoint);
                modifyField(field, field2, 2);
            }

            while (x < 0 || y > 9) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                attackPoint = scanner.nextLine();
                gp.getCoordinates(attackPoint);
            }

            player_counter++;
            System.out.println("\nPress Enter and pass the move to another player");
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }
}
