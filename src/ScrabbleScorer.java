import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Program that uses user input and compares them to a text file, and scores the word
 * @author Kyngston Gaddy
 * @version 01.30.2024
 */
public class ScrabbleScorer {
    private final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int[] points = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};

    private ArrayList<String> dictionary;

    /**
     * Default constructor, initializes the dictionary, imports words
     */
    public ScrabbleScorer() {
        dictionary = new ArrayList<>();
        buildDictionary();
    }

    /**
     * This takes the file and sorts the words
     */
    public void buildDictionary() {
        try {
            Scanner in = new Scanner(new File("SCRABBLE_WORDS.txt"));
            while(in.hasNext()) {
                dictionary.add(in.nextLine());
            }
            in.close();
            Collections.sort(dictionary);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether or not the program is valid
     * @param word,  word from the user input
     * @return determines whether or not the word is valid
     */
    public boolean isValidWord(String word) {
        word = word.toUpperCase();
        return word.length() <= 15 && Collections.binarySearch(dictionary, word) >= 0;
    }

    /**
     * Determines the score of the word
     * @param word, word from the user input
     * @return the score of the word from the user input
     */
    public int getWordScore(String word) {
        word = word.toUpperCase();
        int score = 0;
        for(int i = 0; i < word.length(); i++) {
            score += points[alpha.indexOf(word.charAt(i))];
        }
        return score;
    }

    /**
     * Main method of program
     * @param args command line args, if needed
     */
    public static void main(String[] args) {
        ScrabbleScorer app = new ScrabbleScorer();
        System.out.println("* Welcome to the Scrabble Word Scorer app *");
        Scanner in = new Scanner(System.in);
        String input;

        while(true) {
            System.out.print("Enter a word to score or 0 to quit: ");
            input = in.nextLine();
            if(input.equals("0")) {
                break;
            }
            else {
                if(app.isValidWord(input))
                    System.out.println(input + " = " + app.getWordScore(input) + " points");
                else {
                    System.out.println(input + " is not a valid word in the dictionary");
                }
            }
        }
        System.out.println("Exiting the program. Thanks for playing!");
    }
}
