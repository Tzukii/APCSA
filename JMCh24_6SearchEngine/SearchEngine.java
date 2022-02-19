import java.util.*;

/**
 * Simulates a search engine implemented using maps
 * 
 * @author Shivam Maji
 * @version 2/18/21
 * 
 * @author Period - 6
 * @author Assignment - JM24.6 - Search Engine
 * 
 * @author Sources -
 */
public class SearchEngine {
    // Instance variable(s)
    private String myURL; // holds the name for the "url" (file name)
    private Map<String, List<String>> myIndex; // holds the word index

    /**
     * Constructor :(
     * 
     * @param url URL
     */
    public SearchEngine(String url) {
        this.myURL = url;
        this.myIndex = new HashMap<>(20000);
    }

    /**
     * Gets the URL
     * 
     * @return URL
     */
    public String getURL() {
        return myURL;
    }

    /**
     * Adds a line
     * 
     * @param line The line you want to add
     */
    public void add(String line) {
        for (String obj : parseWords(line)) {
            if (myIndex.containsKey(obj)) {
                myIndex.get(obj).add(line);
            } else {
                myIndex.put(obj, new LinkedList<String>());
                myIndex.get(obj).add(line);
            }
        }

    }

    /**
     * Gets the number of hits
     * 
     * @param word word
     * @return the hits
     */
    public List<String> getHits(String word) {
        return myIndex.get(word);
    }

    /*
     * Parses the words
     * 
     * @param line The line you want to input
     * 
     * @return Output words
     */
    private Set<String> parseWords(String line) {
        TreeSet<String> treeTemp = new TreeSet<>();
        String[] lines = line.toLowerCase().split("\\W+");
        for (String s : lines) {
            treeTemp.add(s);
        }
        return treeTemp;
    }

    // *************************************************************
    // For test purposes only
    // not to be used in solution implementation
    public Map<String, List<String>> getIndex() {
        return myIndex;
    }
}
