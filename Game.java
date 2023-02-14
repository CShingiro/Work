import java.util.Scanner;
import java.util.InputMismatchException;

public class Game {
    private Player[] players;
    private static Scanner scanner = new Scanner(System.in);
    private Board board;
    private String playerOne;
    private String playerTwo;

    public void setUpGame() throws Exception {
        boolean playersNotEntered = true;
        do {
            System.out.println("Please enter Player one's name: ");
            this.playerOne = scanner.nextLine();
            System.out.println("Please enter Player two's name: ");
            this.playerTwo = scanner.nextLine();
            if (this.playerOne.equals(this.playerTwo)) {
                throw new Exception("Both names are the same. Please re-enter new names: ");
            } else {
                playersNotEntered = false;
            }
        } while (playersNotEntered);

        Player p1 = new Player(this.playerOne, 1);
		Player p2 = new Player(this.playerTwo, 2);

        Player[] players = {p1, p2};
        this.players = players;
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
        catch(InvalidMoveException e) {
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
                    break;
                } else {
                	this.board.printBoard();
                    currentPlayerIndex = (currentPlayerIndex + 1) % this.players.length;
                }
            }
        } while(noWinner);
        scanner.close();
    }
}
