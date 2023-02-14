package fourinarowgame;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Game {
    private Player[] players;
    private static Scanner scanner = new Scanner(System.in);
    private Board board;

    public void setUpGame() throws Exception {
        String playerOne;
        String playerTwo;
        System.out.println("Please enter Player one's name: ");
        playerOne = scanner.nextLine();
        System.out.println("Please enter Player two's name: ");
        playerTwo = scanner.nextLine();
        if (playerOne.equals(playerTwo)) {
            throw new InvalidNameException("Both names are the same. Please re-enter new names: ");
        }

        Player p1 = new Player(playerOne, 1);
		Player p2 = new Player(playerTwo, 2);

        Player[] playerList = {p1, p2};
        this.players = playerList;
        this.board = new Board();
        board.boardSetUp();
    }

    public void printWinner(Player player) {
        System.out.println("Congratulations! The winner is " + player + "!");
    }

    public void playerTurn(Player currentPlayer) {
        try{ 
            this.board.addToken(currentPlayer.makeMove(), currentPlayer.getPlayerNumber());
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage() + " Please enter a valid column number.");
        }
        catch(InputMismatchException e) {
            System.out.println("Please enter valid input.");
        }
        catch(ColumnFullException e) {
            System.out.println("Please enter an available column.");
        }
        catch(Exception e) {
        	System.out.println(e.getMessage() + "Something went wrong.");
        }
    }

    public void play() throws Exception {
        boolean noWinner = true;
        setUpGame();
        int currentPlayerIndex = 0;
        do {
            this.board.boardFull();
            if(this.board.boardFull()) {
                System.out.println("Board is now full. Game Ends.");
                return;
            } else {
                Player currentPlayer = this.players[currentPlayerIndex];
                System.out.println("It is player " + currentPlayer.getPlayerNumber() +"'s turn: " + currentPlayer.getName() + ".");
                playerTurn(currentPlayer);
                boolean winner = this.board.checkIfPlayerIsTheWinner(currentPlayer.getPlayerNumber());
                if(winner) {
                    this.board.printBoard();
                    printWinner(currentPlayer);
                    noWinner = false;
                } else {
                	this.board.printBoard();
                    currentPlayerIndex = (currentPlayerIndex + 1) % this.players.length;
                }
            }
        } while(noWinner);
        scanner.close();
    }
}
