/**
 * bruh
 * @author bruh
 * @version bruh
 */

import java.util.ArrayList;

/**
 * bruh
 * @author bruh
 * @version bruh
 */

public class IndexEntry {
    /**
     * bruh
     */
    private String word;
    private ArrayList<Integer> numList;

    /**
     * 
     * @param word bruh
     */

    // Constructs an IndexEntry for a given word
    // (converted to upper case); sets numsList
    // to an empty ArrayList.
    public IndexEntry(String word) {
        word.trim();
        this.word = word.toUpperCase();
        numList = new ArrayList<Integer>();
    }
    /**
     * 
     * @return bruh
     */

    // Returns the word of this IndexEntry object.
    public String getWord() {
        return word;
    }
    /**
     * @param num bruh
     */

    // If num is not already in the list, adds num
    // at the end of this IndexEntry's list
    // of numbers.
    public void add(int num) {
        int num2 = num;
        if (!numList.contains(num2)) {
            numList.add(num2);
        }
    }
    /**
     * @return bruh
     */

    // Converts this IndexEntry into a string in the
    // following format: the word followed by a space, followed by
    // numbers separated by a comma and a space.
    public String toString() {
        return word + " " + numList.toString().
            replaceAll("\\[", "").replaceAll("\\]", "");
    }
}
