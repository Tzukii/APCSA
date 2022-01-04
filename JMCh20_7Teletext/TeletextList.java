import java.awt.Graphics;

/**
 * This class implements the list of messages for teletext
 * 
 * @author Shivam Maji
 * @version 1/3/21
 * 
 * @author Period - T6
 * @author Assignment - Java Methods Ch09 - SnackBar
 * 
 * @author Sources - Me Myself and I
 */

public class TeletextList {
    private ListNode2<String> heading, topNode;

    /**
     * Creates a circular list of headlines. First creates a circular list
     * with one node, "Today's headlines:". Saves a reference to that node in
     * heading. Adds a node holding an empty string before heading and another
     * node holding an empty string after heading. Appends all the strings
     * from headlines to the list, after the blank line that follows heading,
     * preserving their order. Sets topNode equal to heading.
     * 
     * @param headlines Strings to add to circular list
     */
    public TeletextList(String[] headlines) {
        // SYNTAX: ListNode2.ListNode2<String>(String initValue, initPrevious, initNext)

        /*
         * Creates a circular list with 1 node called heading
         */
        heading = new ListNode2<String>("Today's headlines:", null, null);

        /*
         * Adds a node holding an empty string before heading and another node holding
         * an empty string after heading
         */
        heading.setPrevious(new ListNode2<String>("", null, heading));
        heading.setNext(new ListNode2<String>("", heading, null));

        /*
         * Appends all the strings from headlines to the list, after the blank line that
         * follows heading, preserving their order
         */
        ListNode2<String> tempNode = heading.getNext();
        for (String item : headlines) {
            tempNode.setNext(new ListNode2<String>(item, tempNode, null));
            tempNode = tempNode.getNext();
        }
        tempNode.setNext(heading.getPrevious());
        heading.getPrevious().setPrevious(tempNode);

        /*
         * Sets topNode equal to heading.
         */
        topNode = heading;
    }

    /**
     * Inserts a node with msg into the headlines list after the blank /line
     * that follows heading.
     * 
     * @param msg String to add to headlines list
     */
    public void insert(String msg) {
        /*
         * Creates a new node called head
         */
        ListNode2<String> head = heading.getNext();
        topNode = head;

        /*
         * Creates a new node called head2 using head.getNext(). head3 is also created
         */
        ListNode2<String> head2 = head.getNext();
        ListNode2<String> head3 = new ListNode2<String>(msg, head, head2);

        head.setNext(head3);

        head = head3.getNext();
        head = head3.getNext();

        head.setPrevious(head3);
    }

    /**
     * Deletes the node that follows topNode from the headlines list, unless
     * that node happens to be heading or the node before or after heading that
     * holds a blank line.
     */
    public void delete() {
        /*
         * Creates a ListNode with the next value in topNode
         */
        ListNode2<String> temp = topNode.getNext();

        /*
         * Checks if that next value is the same as the heading or a blank line(blank
         * lines surround heading so you can use getNext and getPrevious here)
         */
        if (temp.equals(heading) || temp.equals(heading.getPrevious()) || temp.equals(heading.getNext())) {
            /*
             * If the next value is the same, exit the method
             */
            return;
        }

        /*
         * Else, create a newNode that contains the value after temp. Set topNode to the
         * value 2 lines after heading. Replace the item in topNode with newNode, the
         * value after temp. Set the value before topNode to a blank line
         * 
         * Essentially, you just delete the node that follows topNode from the headlines
         * list.
         */

        ListNode2<String> newNode = temp.getNext();
        topNode = heading.getNext().getNext();
        heading.getNext().setNext(newNode);
        topNode.setPrevious(newNode);
    }

    /**
     * Scrolls up the headlines list, advancing topNode to the next node.
     */
    public void scrollUp() {
        /*
         * Inbuilt function. You get the value after topNode, advancing it to the next
         * node
         */
        topNode = topNode.getNext();
    }

    /**
     * Adds a new node with msg to the headlines list before a given node.
     * Returns a referenece to the added node.
     */
    private ListNode2<String> addBefore(ListNode2<String> node, String msg) {
        /*
         * Create a newNode that contains {node.getPrevious(), msg, node}
         */
        ListNode2<String> newNode = new ListNode2<String>(msg, node.getPrevious(), node);

        /*
         * Set the value between node.getPrevious and node to newNode
         */
        node.getPrevious().setNext(newNode);

        return newNode;
    }

    /**
     * Adds a new node with msg to the headlines list after a given node.
     * Returns a referenece to the added node.
     */
    private ListNode2<String> addAfter(ListNode2<String> node, String msg) {
        /*
         * Create a newNode that contains {node.getPrevious(), msg, node}
         */
        ListNode2<String> newNode = new ListNode2<String>(msg, node.getPrevious(), node);
    
        /*
         * Set the value 2 nodes ahead of node to newNode
         */
        node.getNext().setNext(newNode);

        return newNode;
    }

    /**
     * Removes a given node from the list.
     */
    private void remove(ListNode2<String> node) {
        /*
         * Traverse the array until you reach node using a while loop
         */

        while (!heading.equals(node)) {
            heading = heading.getNext();
        }

        /*
         * Once you reach node, create a newNode that contains a blank space
         */
        ListNode2<String> newNode = heading.getNext().getNext();

        /*
         * Remove the node by overwriting it with newNode
         */
        heading.getPrevious().setNext(newNode);
        newNode.setPrevious(heading.getPrevious());

    }

    /**
     * Draws nLines headlines in g, starting with topNode at x, y and
     * incrementing y by lineHeight after each headline.
     */
    public void draw(Graphics g, int x, int y, int lineHeight, int nLines) {
        ListNode2<String> node = topNode;
        for (int k = 1; k <= nLines; k++) {
            g.drawString(node.getValue(), x, y);
            y += lineHeight;
            node = node.getNext();
        }
    }

    /**
     * Returns a string representation of this TeletextList.
     * 
     * @return a string representation of this TeletextList.
     */
    public String toString() {
        String str = getClass().getName() + "[";
        String separator = "";

        for (ListNode2<String> node = heading; node.getNext() != heading; node = node.getNext()) {
            str += (separator + node.getValue());
            separator = ", ";
        }

        return str + "]";
    }
}
