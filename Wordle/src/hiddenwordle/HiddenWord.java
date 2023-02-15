package hiddenwordle;

public class HiddenWord{

    /** Declare any fields (instance variables) **/
    private String theWord;

    /** Declare a constructor */
    public HiddenWord(String theWord) {
        this.theWord = theWord;
    }

    /** Write the getHint method */
    public String getHint(String guess) {
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<this.theWord.length() && result.length() <= this.theWord.length();i++) {
            if(this.theWord.contains(guess.substring(i,i+1))) {
                if(this.theWord.substring(i,i+1).equals(guess.substring(i,i+1))) {
                    result.append(guess.substring(i,i+1));
                } else {
                    result.append("+");
                }
            }
            else {
                result.append("*");
            }
        }
        return result.toString();
    }


    /** This is a main method for testing the class */
    public static void main(String[] args) {
        HiddenWord puzzle = new HiddenWord("HARPS");
        System.out.println(puzzle.getHint("AAAAA") + " it should print +A+++");
        System.out.println(puzzle.getHint("HELLO") + " it should print H****");
        System.out.println(puzzle.getHint("HEART") + " it should print H*++*");
        System.out.println(puzzle.getHint("HARMS") + " it should print HAR*S");
        System.out.println(puzzle.getHint("HARPS") + " it should print HARPS");

    } // end of main

} // end of class
