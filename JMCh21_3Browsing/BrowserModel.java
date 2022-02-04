import java.util.List;
import java.util.Stack;

/**
 * Java Methods Chapter 20 Exercises
 *
 * @author Shivam Maji
 * @version 1/`10/2021
 * @author Period: 6
 * @author Assignment: JM Ch21.3 - Browsing
 *
 * @author Sources: Me Myself and I
 * 
 * @apiNote Comments within each individual methods themselves were not provided
 *          due to how simple and self explanatory the code was
 */
public class BrowserModel {
    private BrowserView view;
    private Stack<Integer> backStk, forwardStk;
    private int topLine;

    /**
     * Constructor
     */
    public BrowserModel(BrowserView view) {
        this.view = view;
        view.update(0);
        backStk = new Stack<Integer>();
        forwardStk = new Stack<Integer>();
        topLine = 0;
    }

    /**
     * Follows a link to another page
     * 
     * @param n Page to go to
     */
    public void followLink(int n) {
        view.update(n);
        backStk.push(topLine);
        topLine = n;
        forwardStk.clear();
    }

    /**
     * Checks if there is a back page to go to
     * 
     * @return If there is a back page or not
     */
    public boolean hasBack() {
        return !backStk.isEmpty();
    }

    /**
     * Checks if there is a forward page to go to
     * 
     * @return If there is a forward page or not
     */
    public boolean hasForward() {
        return !forwardStk.isEmpty();
    }

    /**
     * Returns to the initial page
     */
    public void home() {
        view.update(0);
        backStk.push(topLine);
        topLine = 0;
        forwardStk.clear();
    }

    /**
     * Goes back a page
     */
    public void back() {
        if (hasBack()) {
            int i = (int) backStk.pop();
            forwardStk.push(topLine);
            topLine = i;
            view.update(i);
        }
    }

    /**
     * Goes forward a page
     */
    public void forward() {
        if (hasForward()) {
            int i = (int) forwardStk.pop();
            backStk.push(topLine);
            topLine = i;
            view.update(i);
        }
    }

    // The following are for test purposes only
    public Stack<Integer> getBackStk() {
        return backStk;
    }

    public Stack<Integer> getForwardStk() {
        return forwardStk;
    }

    public int getTopLine() {
        return topLine;
    }
}
