public class HiddenWord{

    /** Declare any fields (instance variables) **/
    private String hiddenWord;
    private StringBuilder result = new StringBuilder();

    /** Declare a constructor */
    public HiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    /** Write the getHint method */
    public String getHint(String guess) {
        int i = this.hiddenWord.length() - guess.length();
        if(guess.equals(hiddenWord)) {
            return guess;
        }
        else {
            if(guess.length()> 0 && i < guess.length() ) {
                if(modifiedHidden(i).contains(guess.substring(i,i+1))) {
                    if(modifiedHidden(i).substring(i,i+1).equals(guess.substring(i,i+1))) {
                        result.append(guess.substring(i,i+1));
                        result.append(getHint(guess.substring(i+1,guess.length())));
                    } else {
                        result.append("+");
                        result.append(getHint(guess.substring(i + 1,guess.length())));
                    }
                } else {
                    result.append("*");
                    result.append(getHint(guess.substring(i + 1,guess.length())));
                }
            }
        }
        return result.toString();
    }

    public String modifiedHidden(int index) {
        if(index == 0) {
            return this.hiddenWord;
        }
        return this.hiddenWord.substring(index - 1, this.hiddenWord.length());
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
