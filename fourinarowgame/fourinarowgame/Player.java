package fourinarowgame;

import java.util.Scanner;

public class Player {
    private String name;
    private int playerNumber;
    private Scanner scanner = new Scanner(System.in);

    public Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
    }

    public String getName() {
        return this.name;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public int makeMove() {
        System.out.println("Choose the place to drop your coin by column number: ");
        int col = this.scanner.nextInt();
        return col - 1;
    }

    public String toString() {
        return "Player " + this.playerNumber + " is " + this.name;
    }
}
