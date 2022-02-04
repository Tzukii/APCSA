/* comment out the following prior to submission to Web-CAT */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ListIterator;

///**
// *  Testing Stub
// *
// *  @author  bygeorge
// *  @version Jan 29, 2013
// *  @author  Period: all
// *  @author  Assignment: JMCh21_3BrowsingSoln
// *
// *  @author  Sources: none
// */
public class BrowserView {
    int lineNum;

    /**
     * stub constructor
     */
    public BrowserView() {
        lineNum = -1;
    }

    /**
     * Test stub update
     * 
     * @param lNum line number to track
     */
    public void update(int lNum) {
        this.lineNum = lNum;
    }

    /**
     * Test stub to get tracked line number
     * 
     * @return line number
     */
    public int getLineNum() {
        return lineNum;
    }
}
