import java.util.Arrays;

/**
 * Maintains a lookup table for substitutions for the letters ‘A’
 * through ‘Z’
 * 
 * @author Shivam Maji
 * @version 2/5/21
 * 
 * @author Period - 6
 * @author Assignment - JM 24.3 Lab: Cryptogram Solver
 * 
 * @author Sources - Me Myself and I
 */
public class Enigma {
    private char[] lookupTable;
    private int[] counts;
    private static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 
     * @param numLetters The number of letters you want to be in your lookup table.
     */
    public Enigma(int numLetters) {
        lookupTable = new char[numLetters];
    }

    /**
     * Assumes that subs contains 26 characters and saves it as the lookup table.
     * 
     * @param index
     * @param ch
     */
    public void setSubstitution(int index, char ch) {
        for (char c : lookupTable) {
            if (c == index) {
                lookupTable[c] = ch;
            }
        }
    }

    /**
     * Decodes all the letters in text according to the current lookup table. The
     * decode method leaves all characters that are not letters unchanged and
     * preserves the upper or lower case of letters. It returns the decoded string,
     * which has the same length as text
     * 
     * @param text
     * @return
     */
    public String decode(String text) {
        StringBuffer buffer = new StringBuffer(text.length());

        // TODO complete method

        return buffer.toString();
    }

    /**
     * Returns computer-generated hints for each letter in the encrypted text. It
     * works as follows. First it counts the number of occurrences for each of the
     * letters ‘a’ through ‘z’ in text (case blind) and saves these 26 counts in an
     * array
     * 
     * After getting the counts for all letters, getHints creates and returns a
     * String of “hints.” The returned string hints should hold a permutation of
     * letters ‘A’ through ‘Z’; hint.charAt(k) will be displayed as a
     * computer-generated hint for decoding the k-th letter of the alphabet. The
     * hints should be based on comparing the order of letters by frequency in
     * letter counts in encrypted text with the order of letters by frequency in
     * plain (unencrypted) text. The lettersByFrequency parameter contains the
     * letters ‘A’ through ‘Z’ arranged in increasing order of their frequencies in
     * a sample text file.
     * 
     * @param text
     * @param lettersByFrequency
     * @return Returns computer-generated hints for each letter in the encrypted
     *         text. It works as follows. First it counts the number of occurrences
     *         for each of the letters ‘a’ through ‘z’ in text (case blind) and
     *         saves these 26 counts in an array
     */
    public String getHints(String text, String lettersByFrequency) {
        // TODO complete method

        return new String(/* FIX THIS */);
    }

    /**
     * Helper method for getHints. Counts the number of insances of the letters A -
     * Z(case blind) and returns them in an array
     * 
     * @param text The text you want to check
     * @return Returns the number of insances of the letters A - Z(case blind) and
     *         returns them in an array
     */
    private int[] countLetters(String text) {
        counts = new int[lookupTable.length];

        char[] ch = text.toCharArray();
        for (char c : ch) {
            counts[letters.indexOf(c)]++;
        }

        return counts;
    }

    // *************************************************************
    // For test purposes only
    // not to be used in solution implementation

    public char[] getLookupTable() {
        return lookupTable;
    }

    public int[] getCountLetters(String text) {
        return countLetters(text);
    }

}