/**
 * Implements a sorted list of words
 * 
 * @author Shivam Maji
 * @version 12/1
 * @author Period - 6
 * @author Assignment - Java Methods 13.4 Lab: Keeping Things in Order
 * @author Sources - Me Myself and I
 */
public class SortedWordList
    extends java.util.ArrayList<String> {
  public SortedWordList() {
    super();
  }

  public SortedWordList(int capacity) {
    super(capacity);
  }

  public boolean contains(String word) {
    return indexOf(word) >= 0;
  }

  public int indexOf(String elem) {
    int low = 0;
    int high = size() - 1;

    while (low <= high) {
      int mid = (low + high) / 2;
      String midElem = get(mid);

      if (elem.equalsIgnoreCase(midElem))
        return mid;
      else if (elem.compareToIgnoreCase(midElem) < 0)
        high = mid - 1;
      else
        low = mid + 1;
    }

    return -1;
  }

  public String set(int i, String word) {
    String string1 = "";
    String string2 = "";
    String setString = "";

    try {
      string1 = get(i - 1);
    } catch (Exception exception) {
      string1 = "";
    }
    try {
      string2 = get(i + 1);
    } catch (Exception exception) {
      string2 = "";
    }

    if ((string1.equals("") || word.compareTo(string1) > 0) &&
        (string2.equals("") || word.compareTo(string2) < 0)) {
      setString = super.set(i, word);
    } else {
      throw new IllegalArgumentException("word =" + word + " i =" + i);
    }

    return setString;
  }

  public void add(int i, String word) {
    String string1 = "";
    String string2 = "";

    try {
      string1 = get(i - 1);
    } catch (Exception e) {
      string1 = "";
    }
    try {
      string2 = get(i);
    } catch (Exception e) {
      string2 = "";
    }

    if ((string1.equals("") || word.compareTo(string1) > 0) &&
        (string2.equals("") || word.compareTo(string2) < 0)) {
      super.add(i, word);
    } else {
      throw new IllegalArgumentException("word =" + word + " i =" + i);
    }
  }

  public boolean add(String word) {
    int low = 0;
    int high = size() - 1;
    int midElem = 0;

    while (low <= high) {
      midElem = (low + high) / 2;
      int x = word.compareToIgnoreCase(get(midElem));

      try {
        add(midElem, word);
      }

      catch (Exception e) {
        if (x > 0) {
          low = midElem + 1;
        } else if (x < 0) {
          high = midElem - 1;
        } else if (x == 0) {
          return false;
        }
      }
    }

    super.add(word);
    return true;
  }

  public void merge(SortedWordList additionalWords) {
    for (String string : additionalWords) {
      add(string);
    }
  }
}
