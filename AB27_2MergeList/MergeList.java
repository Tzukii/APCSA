import java.util.*;
import java.io.*;

/**
 * Implements a recursive mergesort on a LinkedList data type
 * 
 * @author Shivam Maji
 * @version 12/8/21
 * 
 * @author Period - 6
 * @author Assignment - AB27.1 MergeList
 * 
 * @author Sources - Me Myself and I
 */
public class MergeList {
    private Scanner inFile;
    private String myFile;

    /**
     * Open a file containing id/inventory pairs of data
     * 
     * @param fileName File to be opened
     */
    public MergeList(String fileName) {
        myFile = fileName;
    }

    /**
     * Reads a file containing id/inv data pairs. The first line of the file
     * contains the number of id/inventory integer pairs listed on subsequent
     * lines.
     * 
     * @param list Reference to LinkedList<Item> to which data will be added
     */
    public void readData(LinkedList<Item> list) {
        int id;
        int inv;

        try {
            inFile = new Scanner(new File(myFile));
            while (inFile.hasNext()) {
                id = inFile.nextInt();
                inv = inFile.nextInt();
                list.addFirst(new Item(id, inv));
            }
        } catch (IOException i) {
            System.out.println("Error: " + i.getMessage());
        }
    }

    /**
     * Prints contents of list
     * 
     * @param list LinkedList<Item> to be printed
     */
    public void printList(LinkedList<Item> list) {
        Iterator<Item> iter = list.iterator();

        System.out.printf("%5s%16s", "Id", "Inventory\n");
        while (iter.hasNext()) {
            Item temp = iter.next();
            System.out.printf("%7d%10d\n", temp.getId(), temp.getInv());
        }
        System.out.println();
    }

    /**
     * Splits listA into two parts. listA retains the first half of the list,
     * listB contains the last half of the original list.
     * 
     * @param listA Original list. reduced by half after split.
     * @param listB Contains last half of the original list
     */
    public void split(LinkedList<Item> listA, LinkedList<Item> listB) {

        int size = listA.size() / 2;

        for (int i = 0; i < size; i++) {
            listB.addFirst(listA.removeLast());
        }

    }

    /**
     * Two linked lists listA and listB are merged into a single linked list
     * mergedList. They are placed in mergedList starting with the smallest item
     * on either list and continue working up to to largest item.
     * 
     * @param listA LinkedList<Item> in nondecreasing order
     * @param listB LinkedList<Item> in nondecreasing order
     * @return LinkedList<Item> containing all the values from lists listA
     *         and listB, in nondecreasing order
     */
    public LinkedList<Item> merge(
            LinkedList<Item> listA,
            LinkedList<Item> listB) {

        LinkedList<Item> mergedList = new LinkedList<Item>();

        while (!listA.isEmpty() && !listB.isEmpty()) {
            Item list1 = listA.peek();
            Item list2 = listB.peek();
            if (list1.compareTo(list2) <= 0) {
                mergedList.add(listA.poll());
            } else {
                mergedList.add(listB.poll());
            }
        }
        mergedList.addAll(listA);
        mergedList.addAll(listB);

        return mergedList;

    }

    /**
     * The linked list is returned in sorted order. Sorted order has the smallest
     * item first and the largest item last. The ordering is determined by the
     * order defined in the Comparable class. The method uses the mergesort
     * technique and must be recursive.
     * 
     * @param listA LinkedList<Item> to be sorted
     * @return LinkedList<Item> in sorted (nondecreasing) order
     */
    public LinkedList<Item> mergeSort(LinkedList<Item> listA) {
        LinkedList<Item> listB = new LinkedList<Item>();

        if (listA == null) {
            return null;
        }

        if (listA.size() <= 1) {
            return listA;
        }
        split(listA, listB);

        return merge(mergeSort(listA), mergeSort(listB));
    }

    /**
     * Reverses the order of a list
     * 
     * @param list LinkedList<Item> to be reversed
     * @return LinkedList<Item> in reverse order
     */
    public LinkedList<Item> reverseList(LinkedList<Item> list) {
        LinkedList<Item> reversedList = new LinkedList<Item>();

        while (!list.isEmpty()) {
            reversedList.add(list.removeLast());
        }

        return reversedList;

    }
}
