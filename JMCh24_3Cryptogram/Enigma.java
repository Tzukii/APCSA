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

    /**
     * Constructor
     * 
     * @param numLetters The number of letters you want to be in your lookup table.
     */
    public Enigma(int numLetters) {
        lookupTable = new char[numLetters];
    }

    /**
     * Assumes that subs contains 26 characters and saves it as the lookup table.
     * 
     * @param index Index to add to
     * @param ch    Character to add
     */
    public void setSubstitution(int index, char ch) {
        lookupTable[index] = ch;
    }

    /**
     * Decodes all the letters in text according to the current lookup table. The
     * decode method leaves all characters that are not letters unchanged and
     * preserves the upper or lower case of letters. It returns the decoded string,
     * which has the same length as text
     * 
     * @param text Text to decode
     * @return Decoded text
     */
    public String decode(String text) {
        StringBuffer buffer = new StringBuffer(text.length());

        for (char c : text.toCharArray()) {
            int i = Character.getNumericValue(Character.toUpperCase(c)) - 10;
            buffer.append(
                    Character.isLetter(c)
                            ? Character.isUpperCase(c) ? Character.toUpperCase(lookupTable[i])
                                    : Character.toLowerCase(lookupTable[i])
                            : c);
        }
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
     * @param text               Input Text
     * @param lettersByFrequency Frequency of each letter
     * @return Returns computer-generated hints for each letter in the encrypted
     *         text. It works as follows. First it counts the number of occurrences
     *         for each of the letters ‘a’ through ‘z’ in text (case blind) and
     *         saves these 26 counts in an array
     */
    public String getHints(String text, String lettersByFrequency) {
        char[] hints = new char[lookupTable.length];
        int[] counts = countLetters(text);

        for (int i = 0; i < counts.length; i++) {
            int temp = 0;
            for (int j = 0; j < counts.length; j++) {
                if (counts[j] < counts[i] ||
                        (counts[j] == counts[i] && j < i)) {
                    temp++;
                }
            }
            hints[i] = lettersByFrequency.charAt(temp);
        }

        return new String(hints);
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
        int[] counts = new int[lookupTable.length];

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                counts[Character.getNumericValue(c) - 10]++;
            }
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