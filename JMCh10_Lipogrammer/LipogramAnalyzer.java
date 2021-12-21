/**
 * Bruh Bruh
 * 
 * @author Bruh
 * @version Bruh
 * 
 * @author Period - Bruh
 * @author Assignment - JMCh10 Lipogrammer
 * 
 * @author Sources - Bruh
 */
public class LipogramAnalyzer {
    private String text;

    /**
     * Constructor: Saves the text string
     * 
     * @param text String to analyze
     */
    public LipogramAnalyzer(String text) {
        this.text = text;
    }

    /**
     * Returns the text string with all characters equal to letter replaced with
     * '#'.
     * 
     * @param letter character to replace
     * @return text string with all characters equal to letter replaced with '#'
     */
    public String mark(char letter) {
        String s3 = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == letter)
                s3 = s3 + "#";
            else
                s3 = s3 + text.charAt(i);
        }
        return s3; // !!!Fix this
    }

    /**
     * Returns a String that concatenates all "offending" words from text that
     * contain letter; the words are separated by '\n' characters; the returned
     * string does not contain duplicate words: each word occurs only once; there
     * are no punctuation or whitespace characters in the returned string.
     * 
     * @param letter character to find in text
     * @return String containing all words with letter
     */
    public String allWordsWith(char letter) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == letter) {
                if (result.indexOf(extractWord(i)) == -1)
                    result = result + extractWord(i) + '\n';
            }
        }

        return result;
    }

    // made public for test purposes
    /*
     * Returns the word that contains character at pos excluding any punctuation or
     * whitespace.
     * 
     * @param pos location of character
     * 
     * @return word that contains character at pos
     */
    public String extractWord(int pos) {
        int r = pos + 1;
        while (r < text.length()) {
            if (text.charAt(r) == ' ')
                break;
            r++;
        }
        int l = pos - 1;
        while (l >= 0) {
            if (text.charAt(l) == ' ' || l == 0)
                break;
            l--;
        }
        if (l == 0)
            return text.substring(l, r);
        return text.substring(l + 1, r); // !!!Fix this
    }
}
