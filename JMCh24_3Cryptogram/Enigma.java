import java.util.Arrays;

/**
   TODO Write a one-sentence summary of your class here.
   TODO Follow it with additional details about its purpose, what abstraction
   it represents, and how to use it.

   @author  TODO Your Name
   @version TODO Date

   @author Period - TODO Your Period
   @author Assignment - JM 24.3 Lab: Cryptogram Solver

   @author Sources - TODO list collaborators
 */
public class Enigma
{
    private char[] lookupTable;

    public Enigma(int numLetters)
    {
        // TODO complete constructor
    }

    public void setSubstitution(int index, char ch)
    {
        // TODO complete method
    }

    public String decode(String text)
    {
        StringBuffer buffer = new StringBuffer(text.length());

        // TODO complete method

        return buffer.toString();
    }

    public String getHints(String text, String lettersByFrequency)
    {
        // TODO complete method

        return new String(/* FIX THIS */);
    }

    private int[] countLetters(String text)
    {
        int[] counts = new int[lookupTable.length];

        // TODO complete method

        return counts;
    }

    //*************************************************************
    // For test purposes only
    // not to be used in solution implementation

    public char[] getLookupTable()
    {
        return lookupTable;
    }

    public int[] getCountLetters(String text)
    {
        return countLetters(text);
    }

}