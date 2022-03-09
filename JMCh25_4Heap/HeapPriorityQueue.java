import java.util.*;

/**
 * Implements a priority queue based on a min-heap.
 * 
 * @author Me, Myself and I
 * @version Date
 * 
 * @author Period - 6
 * @author Assignment - JMCh25_4HeapOfTrouble
 * 
 * @author Sources - Me Myself and I
 */
public class HeapPriorityQueue {
    private static final int DFLT_CAPACITY = 101;
    private Object[] items;
    private int numItems;
    private final Comparator<Object> comparator;

    public HeapPriorityQueue() {
        this(DFLT_CAPACITY, null);
    }

    public HeapPriorityQueue(Comparator<Object> c) {
        this(DFLT_CAPACITY, c);
    }

    public HeapPriorityQueue(int initialCapacity) {
        this(initialCapacity, null);
    }

    public HeapPriorityQueue(int initialCapacity, Comparator<Object> c) {
        items = new Object[initialCapacity + 1];
        comparator = c;
    }

    /**
     * Returns true if this priority queue is empty;
     * otherwise returns false.
     */
    public boolean isEmpty() {
        return numItems == 0;
    }

    /**
     * Returns the highest priority element without removing
     * it from this priority queue.
     */
    public Object peek() {
        if (numItems == 0) {
            throw new NoSuchElementException();
        }
        return items[1];
    }

    /**
     * Adds obj to this priority queue; returns true.
     */
    public boolean add(Object obj) {
        numItems++;
        if (numItems >= items.length) // items[0] is not used
            growCapacity();
        items[numItems] = obj;
        reheapUp();
        return true;
    }

    /**
     * Removes and returns the highest priority item.
     */
    public Object remove() {
        if (numItems == 0) {
            throw new NoSuchElementException();
        }

        Object minObject = items[1];
        items[1] = items[numItems];
        numItems--;
        reheapDown();
        return minObject;
    }

    // **************************************************************************

    private boolean lessThan(Object obj1, Object obj2) {
        if (comparator != null)
            return comparator.compare(obj1, obj2) < 0;
        else
            return ((Comparable) obj1).compareTo(obj2) < 0;
    }

    public void reheapDown() {
        int curr = 1;
        int left = curr * 2;
        int right = curr * 2 + 1;
        int lowest;

        while (left <= numItems &&
                (lessThan(items[left], items[curr]) ||
                        lessThan(items[right], items[curr]))) {
            if (left == numItems)
                lowest = left;
            else
                lowest = lessThan(items[left], items[right]) ? left : right;

            Object temp = items[curr];
            items[curr] = items[lowest];
            items[lowest] = temp;

            curr = lowest;
            left = curr * 2;
            right = curr * 2 + 1;
            if (right > numItems) {
                right--;
            }
        }
    }

    public void reheapUp() {
        if (numItems == 0 && items.length == 0)
            return;

        int index = numItems;
        Comparable root = (Comparable<Object>) (items[index / 2]);
        while (root != null && lessThan(items[index], root) && index != 0) {
            Object lastleaf = items[index];
            items[index] = items[index / 2];
            items[index / 2] = lastleaf;
            index /= 2;
            root = (Comparable<Object>) (items[index / 2]);
        }
    }

    private void growCapacity() {
        Object[] tempItems = new Object[2 * items.length - 1];
        System.arraycopy(items, 0, tempItems, 0, items.length);
        items = tempItems;
    }

    /**
     * returns the String containing all the elements in level order
     * 
     * @return the String containing all the elements in level order
     */
    public String toString() {
        // TODO complete method
        return null; // Fix This!!!
    }
}
