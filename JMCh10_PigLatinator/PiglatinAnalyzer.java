import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Create an English to Pig Latin translator.
 * 
 * @author Shivam Maji
 * @version Due 10/22/21
 * 
 * @author Period - 6
 * @author Assignment - Lab: JM Ch10 - Piglatinator
 * 
 * @author Sources - Me Myself and I
 */
public class PiglatinAnalyzer {
    private String text;

    // Constructor: saves the text string
    public PiglatinAnalyzer(String text) {
        this.text = text;
    }

    /**
     * Converts a string to it piglatin form according to the following rules: a. If
     * there are no vowels in englishWord, then pigLatinWord is just englishWord +
     * "ay". (There are ten vowels: 'a', 'e', 'i', 'o', and 'u', and their uppercase
     * counterparts.) b. Else, if englishWord begins with a vowel, then pigLatinWord
     * is just englishWord + "yay". c. Otherwise (if englishWord has a vowel in it
     * and yet doesn't start with a vowel), then pigLatinWord is end + start + "ay",
     * where end and start are defined as follows: 1. Let start be all of
     * englishWord up to (but not including) its first vowel. 2. Let end be all of
     * englishWord from its first vowel on. 3. But, if englishWord is capitalized,
     * then capitalize end and "uncapitalize" start.
     *
     * @return piglatin version of text as a String
     */
    public String phraseToPigLatin() {
        String phraseToTranslate = text;
        String outputString = ""; 

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isAlphabetic(c)) {
                String word = extractWord(text, i);
                outputString += wordToPigLatin(word);
                i += word.length() - 1;
            } else {
                outputString += c;
            }
        }

        return outputString;
    }

    /**
     * Converts an "english" word to its piglatin form
     *
     * @param englishWord a string representing an english word
     * @return piglatin form of the english word
     */
    public String wordToPigLatin(String englishWord) {
        String pigLatinWord = "";
        int vowelAt;

        if (isVowel(englishWord.charAt(0))) {
            return englishWord + "yay";
        }

        for (int i = 0; i < englishWord.length(); i++) {
            if (isVowel(englishWord.charAt(i))) {
                vowelAt = i;
                String start = englishWord.substring(0, i);
                System.out.println(start);
                String end = englishWord.substring(i);

                if (Character.isUpperCase(start.charAt(0))) {
                    end = end.substring(0, 1).toUpperCase() + end.substring(1);
                    start = start.toLowerCase();
                }

                pigLatinWord = end + start + "ay";  
                return pigLatinWord;
            }
        }

        return englishWord + "ay";

        /*
         * for (int i = 0; i < englishWord.length(); i++) { if
         * (isVowel(englishWord.charAt(i))) { if (isVowel(englishWord.charAt(0))) {
         * pigLatinWord = englishWord + "yay"; break; } else { // 1 String start =
         * englishWord.substring(0, i); start.toLowerCase(); String end =
         * englishWord.substring(i);
         * 
         * if (Character.isUpperCase(start.charAt(0))) { end = end.substring(0,
         * 1).toUpperCase() + end.substring(1); }
         * 
         * pigLatinWord = end + start + "ay"; break; } } else { pigLatinWord =
         * englishWord + "ay"; } }
         */

    }

    public String extractWord(String text, int start) {
        int e = start + 1;
        while (e < text.length() && Character.isAlphabetic(text.charAt(e))) {
            e++;
        }

        return text.substring(start, e);
    }

    public boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }

}