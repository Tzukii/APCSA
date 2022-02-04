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

public class DocumentIndex extends ArrayList<IndexEntry> {
    
    /**
     * sus amongus
     */
    // Creates an empty DocumentIndex with the default
    // initial capacity.
    public DocumentIndex() {
        super(10);
    }

    /**
     * 
     * @param initialCapacity bruh
     */

    // Creates an empty DocumentIndex with a given
    // initial capacity.
    public DocumentIndex(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * 
     * 
     * @param word bruh
     * @param num bruh
     */

    // If word is not yet in this DocumentIndex,
    // creates a new IndexEntry for word, and inserts
    // it into this list in alphabetical order;
    // adds num to this word's IndexEntry by calling
    // its add(num) method.
    public void addWord(String word, int num) {
        if (word.equals("")) {
            return;
        }
        int loc = foundOrInserted(word);
        get(loc).add(num);
    }

    /**
     * 
     * @param str bruh
     * @param num nrujh
     */

    // For each word in str, calls addWord(word, num).
    public void addAllWords(String str, int num) {
        str.trim();
        for (String word : str.split("\\W+")) {
            addWord(word, num);
        }
    }

    /**
     * 
     * @param word nurh
     * @return bruh
     */

    // Tries to find an EndexEntry with a given word in this
    // DocumentIndex. If not found, inserts a new EndexEntry for
    // word at the appropriate place (in lexicographical order).
    // Returns the index of the found or inserted IndexEntry
    private int foundOrInserted(String word) {
        String compareWord = word.toUpperCase();
        int compareVal;
        for (int i = 0; i < size(); i++) {
            compareVal = compareWord.compareTo(get(i).getWord());
            if (compareVal == 0) {
                return i;
            } 
            else if (compareVal < 0) {
                add(i, new IndexEntry(word));
                return i;
            }
        }
        add(new IndexEntry(word));
        return size() - 1;
    }
}
