package fourinarowgame;

public class InvalidMoveException extends ArrayIndexOutOfBoundsException {
    private static final long serialVersionUID = 1L;

    public InvalidMoveException(String message) {
        super(message);
    }
}
