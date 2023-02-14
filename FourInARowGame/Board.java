import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private String[][] board;
    public int inHorizontalSequence;
    public int inVerticalSequence;
    public int inRightDiagonalSequence;
    public int inLeftDiagonalSequence;
    private static Scanner scanner = new Scanner(System.in);

    public void boardSetUp() throws Exception {
        System.out.print("Please enter the amount of rows for the board: ");
        int row = Board.scanner.nextInt();
        System.out.print("Please enter the amount of columns for the board: ");
        int col = Board.scanner.nextInt();
        if(row >= 4 && col >= 4 && row <= 10 && col <= 10) {
        	this.board = new String[row][col];
        	for(int i = 0;i < this.board.length;i++) {
        		for(int j = 0;j < this.board[i].length;j++) {
        			this.board[i][j] = "-";
        		}
        	}
        } else {
        	System.out.println("Please enter valid board size between 4 and 10 columns and rows.");
        }
        printBoard();
    }

    public void printBoard() throws Exception {
        System.out.println(Arrays.deepToString(this.board));
    }

    public boolean columnFull(int col) {
        boolean isColumnFull;
        if(this.board[this.board.length - 1][col] == "-") {
            isColumnFull = false;
        } else {
            isColumnFull = true;
        }
        return isColumnFull;
    }

    public boolean boardFull() throws Exception {
        boolean isBoardFull = false;
        for(int i = 0;i < this.board.length;i++) {
            for(int j = 0;j < this.board[i].length;j++) {
                if(this.board[i][j] == "-") {
                    isBoardFull = false;
                } else {
                    isBoardFull = true;
                }
            }
        }
        return isBoardFull;
    }

    public boolean addToken(int colToAddToken, int playerNumber) throws ColumnFullException {
        boolean tokenAdded = false;
        do {
            if (columnFull(colToAddToken)) {
                throw new ColumnFullException("Column Full.");
            } else {
                for(int i = 0;i < this.board.length && !tokenAdded;i++) {
                    if (this.board[i][colToAddToken] == "-") {
                        this.board[i][colToAddToken] = Integer.toString(playerNumber);
                        tokenAdded = true;
                    }
                }
            }
        } while(tokenAdded == false);
        return tokenAdded;
    }

    public boolean checkIfPlayerIsTheWinner(int playerNumber) throws Exception {
        return checkVertical(playerNumber) || checkHorizontal(playerNumber) || checkRightDiagonal(playerNumber) || checkLeftDiagonal(playerNumber); 
    }

    public boolean checkVertical(int playerNumber) throws Exception {
        boolean playerWonVertical = false;
        for(int i = this.board.length - 1;i >= 0 && !playerWonVertical;i--) {
            for(int j = this.board.length - 1;j >= 0 && !playerWonVertical;j--) {
                if(this.board[i][j].equals(Integer.toString(playerNumber))) {
                	inVerticalSequence = 0;
                    for(int k = i;k >= 0 && !playerWonVertical;k--) {
                    	if (this.board[k][j].equals(Integer.toString(playerNumber))) {
                    		inVerticalSequence++;
                    	} else {
                    		break;
                    	}
                    	if (inVerticalSequence == 4) {
                    		playerWonVertical = true;
                    	}
                    }
                }
            }
        }
        return playerWonVertical;    
    }

    public boolean checkHorizontal(int playerNumber) throws Exception {
        boolean playerWonHorizontal = false;
        for(int i = this.board.length - 1;i >= 0 && !playerWonHorizontal;i--) {
            for(int j = 0;j < this.board[i].length && !playerWonHorizontal;j++) {
                if(this.board[i][j].equals(Integer.toString(playerNumber))) {
                	inHorizontalSequence = 0;
                	for(int k = j;k< this.board[i].length && !playerWonHorizontal;k++) {
                		if(this.board[i][k].equals(Integer.toString(playerNumber))) {
                			inHorizontalSequence++;
                		} else {
                			break;
                		}
                		if (inHorizontalSequence == 4) {
                			playerWonHorizontal = true;
                		}
                	}
                }
            }
        }
        return playerWonHorizontal;     
    }

    public boolean checkRightDiagonal(int playerNumber) {
        boolean playerWonRightDiagonal = false;
        for(int i = this.board.length - 1;i >= 0 && !playerWonRightDiagonal;i--) {
            for(int j = 0;j < this.board[i].length && !playerWonRightDiagonal;j++) {
                if(this.board[i][j].equals(Integer.toString(playerNumber))) {
                	int a = j;
                	inRightDiagonalSequence = 0;
                    for(int k = i;k >= 0 && !playerWonRightDiagonal && a < this.board.length;k--) {
                    	if (this.board[k][a].equals(Integer.toString(playerNumber))) {
                    		inRightDiagonalSequence++;
                    	} else {
                    		break;
                    	}
                    	if(inRightDiagonalSequence == 4) {
                    		playerWonRightDiagonal = true;
                    	}
                    	a++;
                    }
                }
            }
        }
        return playerWonRightDiagonal;
    }

    public boolean checkLeftDiagonal(int playerNumber) throws Exception {
        boolean playerWonLeftDiagonal = false;
        for(int i = this.board.length -1 ;i >= 0 && !playerWonLeftDiagonal;i--) {
            for(int j = this.board.length - 1;j >= 0 && !playerWonLeftDiagonal;j--) {
                if(this.board[i][j].equals(Integer.toString(playerNumber))) {
                	int a = j;
                	inLeftDiagonalSequence = 0;
                	for(int k = i;k>=0 && !playerWonLeftDiagonal && a >= 0;k--) {
                		if (this.board[k][a].equals(Integer.toString(playerNumber))) {
                			inLeftDiagonalSequence++;
                		} else {
                			break;
                		}
                		if(inLeftDiagonalSequence == 4) {
                			playerWonLeftDiagonal = true;
                		}
                		a--;
                	}
                }
            }
        }
        return playerWonLeftDiagonal;
    }
}
