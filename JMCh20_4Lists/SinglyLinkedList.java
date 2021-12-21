import java.util.Iterator;

/**
 * Implements a singly-linked list.
 * 
 * @author Shivam Maji
 * @version 12/6/2021
 * 
 * @author Period - 6
 * @author Assignment - Lab: JM Ch20.4 - Singly-Linked List
 * 
 * @author Sources - Maria & Gary Litvin
 */
public class SinglyLinkedList<E> implements Iterable<E> {
    private ListNode<E> head;
    private int nodeCount;

    // Constructor: creates an empty list
    public SinglyLinkedList() {
        head = null;
        nodeCount = 0;
    }

    /**
     * Constructor: creates a list that contains all elements from the
     * array values, in the same order
     * 
     * @param values array containing all elements for this list
     */
    public SinglyLinkedList(E[] values) {
        ListNode<E> tail = null;
        for (E value : values) // for each value to insert
        {
            ListNode<E> node = new ListNode<E>(value, null);
            if (head == null)
                head = node;
            else
                tail.setNext(node);
            tail = node;
        }

        nodeCount = values.length;
    }

    /**
     * Return true if this list is empty; otherwise returns false.
     * 
     * @return true if this list is empty; otherwise returns false.
     */
    public boolean isEmpty() {
        if (nodeCount == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return number of elements in this list.
     */
    public int size() {
        return nodeCount;
    }

    /**
     * Returns true if this list contains an Object equal to obj; otherwise returns
     * false.
     * 
     * @return true if this list contains an Object equal to obj
     */
    public boolean contains(Object obj) {
        boolean var = false;
        if (indexOf(obj) >= 0) {
            var = true;
        }
        return var;
    }

    /**
     * Returns the index of the first Object equal to obj; if not found,
     * returns -1.
     * 
     * @param obj Object to find
     * @return the index of the first Object in equal to obj; if not found,
     *         returns -1.
     */
    public int indexOf(Object obj) {
        int counter = 0;
        for (ListNode node = head; node != null; node = node.getNext(), counter++) {
            if (obj.equals(node.getValue()))
                return counter;
        }

        return -1;
    }

    /**
     * Adds obj to this collection. Returns true if successful;
     * otherwise returns false.
     * 
     * @param obj element to add to this collection
     * @return true if successful; otherwise returns false.
     */
    public boolean add(E obj) {
        add(nodeCount, obj);

        return true;
    }

    /**
     * Removes the first element that is equal to obj, if any.
     * Returns true if successful; otherwise returns false.
     * 
     * @param obj element to remove
     * @return true if successful; otherwise returns false.
     */
    public boolean remove(E obj) {
        Object result = null;
        ListNode before = head;

        for (ListNode node = head; node != null; node = node.getNext()) {
            if (node.getValue().equals(obj)) {
                if (node == head) {
                    result = head.getValue();
                    head = head.getNext();
                } else {
                    result = node.getValue();
                    before.setNext(node.getNext());
                }
                nodeCount--;
            } else
                before = node;
        }

        if (result != null) {
            return true;
        }
        return false;

    }

    /**
     * Returns the i-th element.
     * 
     * @param i element to get from the list
     * @return element at index i
     * @throws IndexOutOfBoundsException
     */
    public E get(int i) {
        if (isEmpty() || i >= nodeCount || i < 0) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }

        int counter = 0;
        Object ret = null;

        for (ListNode node = head; node != null; node = node.getNext(), counter++) {
            if (counter == i) {
                ret = node.getValue();
            }
        }
        return (E) ret;
    }

    /**
     * Replaces the i-th element with obj and returns the old value.
     * 
     * @param i   index of element to replace
     * @param obj replacement element of element at index i
     * @return old value previously located at index i
     * @throws IndexOutOfBoundsException
     */
    public E set(int i, E obj) {
        if (isEmpty() || i >= nodeCount || i < 0) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }

        int counter = 0;
        Object ret = null;

        for (ListNode node = head; node != null; node = node.getNext(), counter++) {
            if (counter == i) {
                ret = node.getValue();
                node.setValue(obj);
            }
        }
        return (E) ret;
    }

    /**
     * Inserts obj to become the i-th element. Increments the size
     * of the list by one.
     * 
     * @param i   insertion point in list for obj
     * @param obj element to insert into list
     * @throws IndexOutOfBoundsException
     */
    public void add(int i, E obj) {
        if (nodeCount == 0 && i != 0)
            return;
        if (!(i <= nodeCount && i >= 0))
            return;

        int counter = 0;
        ListNode before = head;

        if (head == null) {
            head = new ListNode(obj, null);
            nodeCount++;
            return;
        }

        for (ListNode node = head; counter <= nodeCount; node = node.getNext(), counter++) {
            if (counter == i) {
                if (i == nodeCount) {
                    before.setNext(new ListNode(obj, null));
                } else if (i == 0) {
                    head = new ListNode(obj, head);
                } else {
                    before.setNext(new ListNode(obj, node));
                }
                nodeCount++;
                return;
            } else {
                before = node;
            }
        }
    }

    /**
     * Removes the i-th element and returns its value.
     * Decrements the size of the list by one.
     * 
     * @param i index of element to remove
     * @return element removed from this list
     */
    public E remove(int i) {
        if (i >= size() || i < 0) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
        ListNode<E> currentNode = head;

        if (i == 0) {
            head = head.getNext();
            nodeCount--;
            return currentNode.getValue();
        }

        int j;
        for (j = 0; j < i - 1; j++) {
            currentNode = currentNode.getNext();
        }
        E ret = currentNode.getNext().getValue();
        currentNode.setNext(currentNode.getNext().getNext());
        nodeCount--;
        return ret;
    }

    /**
     * Returns a string representation of this list.
     * 
     * @return a string representation of this list.
     */
    public String toString() {
        String retString = "";
        for (ListNode node = head; node != null; node = node.getNext()) {
            retString = retString + node.getValue() + ", ";
        }
        return retString;
    }

    /**
     * Returns an iterator for this collection.
     * 
     * @return an iterator for this collection.
     */
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>(head);
    }
}
