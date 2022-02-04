import java.util.Scanner;
import java.io.*;

/**
 * You will start with a text file, words.txt, which contains about 20,000
 * English words. Your program (a simple console application) should choose the
 * three-, four-, and five-letter words from words.txt, convert them into the
 * upper case, and write them to an output file. Make the output file use the
 * syntax of a Java class, as shown in Figure 14-7
 * 
 * Use a Scanner to read words from words.txt and a PrintWriter to create
 * RamblecsDictionary.java
 * 
 * @author Shivam Maji
 * @version 11/21/21
 * 
 * @author Period - 6
 * @author Assignment - JM Ch14 - DictionaryMaker
 * 
 * @author Sources - Me Myself and I
 */
public class DictionaryMaker {
    private static final int MINLENGTH = 3, MAXLENGTH = 5;
    public static Scanner fileIn; // public for test purposes
    public static PrintWriter fileOut; // public for test purposes

    public static void main(String[] args) throws IOException {
        Scanner kboard = new Scanner(System.in);

        System.out.println();
        System.out.print("Enter input file name: ");
        String fileName = kboard.next();

        openInputFile(fileName);
        if (fileIn == null) {
            System.out.println("*** Can't open " + fileName + " ***");
            return;
        }

        createOutputFile("RamblecsDictionary.java");
        if (fileOut == null) {
            fileIn.close();
            System.out.println("*** Can't create RamblecsDictionary.java ***");
            return;
        }

        int count = copyWords();
        System.out.println("Done: " + count + " words.");

        fileIn.close();
        fileOut.close();
    }

    /**
     * Opens a file fileName (in the current folder) and places a reference to it
     * into fileIn
     * 
     * @param fileName Input the file name. Make sure you spell it correctly
     */
    public static void openInputFile(String fileName) {
        File wordList = new File(fileName);
        try {
            fileIn = new Scanner(wordList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates a new file fileName (in the current folder) and places a reference to
     * it into fileOut
     * 
     * @param fileName This is what you want the output file to be named
     */
    public static void createOutputFile(String fileName) {
        File wordOutput = new File(fileName);
        try {
            fileOut = new PrintWriter(wordOutput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reads all words from fileIn, selects the words that have from MINLENGTH to
     * MAXLENGTH letters, converts them into upper case and writes the words into
     * fileOut in Java syntax
     * 
     * @return number of words processed
     */
    public static int copyWords() {

        int counter = 0;
        fileOut.println("public class RamblecsDictionary");
        fileOut.println("{");
        fileOut.println("    public String words[] =");
        fileOut.println("    {");
        while (fileIn.hasNext()) {
            String string = fileIn.next();
            if (string.length() >= MINLENGTH && string.length() <= MAXLENGTH) {
                fileOut.println("        \"" + string.toUpperCase() + "\",");
                counter++;
            }
        }
        fileOut.println("    };");
        fileOut.println("}");

        return counter;
    }
}
