import java.util.Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a BST with TreeNode nodes.
 * 
 * @author Shivam Maji
 * @version 1/28/21
 * 
 * @author Period - 6
 * @author Assignment - Lab: JM Ch23.5 - Do-It-Yourself BST (p. 586-591)
 * 
 * @author Sources - Me Myself and I
 */
public class MyTreeSet<E> implements Iterable<E> {
    private TreeNode<E> root; // holds the root of this BST

    /**
     * Constructor: creates an empty BST.
     */
    public MyTreeSet() {
        root = null;
    }

    /**
     * Returns true if this BST contains value; otherwise returns false.
     * 
     * @param value the value you want to check for
     * @return Returns true if this BST contains value; otherwise returns false.
     */
    public boolean contains(E value) {
        return contains(root, value);
    }

    /**
     * Adds value to this BST, unless this tree already holds value. Returns true if
     * value has been added; otherwise returns false.
     * 
     * @param value The value you want to add
     * @return Returns true if value has been added; otherwise returns false.
     */
    public boolean add(E value) {
        if (contains(value))
            return false;
        root = add(root, value);
        return true;
    }

    /**
     * Removes value from this BST. Returns true if value has been
     * found and removed; otherwise returns false.
     * 
     * @param value The value you want to remove
     * @return Returns true if value has been
     *         found and removed; otherwise returns false.
     */
    public boolean remove(E value) {
        if (!contains(value))
            return false;
        root = remove(root, value);
        return true;
    }

    /**
     * Returns a string representation of this BST.
     */
    public String toString() {
        String str = toString(root);
        if (str.endsWith(", "))
            str = str.substring(0, str.length() - 2);
        return "[" + str + "]";
    }

    /**
     * Returns an iterator for this BST.
     */
    public Iterator<E> iterator() {
        Iterator<E> iter = new MyTreeSetIterator(root);
        return iter;
    }

    // *************** Private helper methods: *********************

    /**
     * Returns true if the BST rooted at node contains value;
     * otherwise returns false (recursive version).
     * 
     * @param node  The node you want to check
     * @param value The value you want to check for
     * @return Returns true if the BST rooted at node contains value;
     *         otherwise returns false (recursive version).
     */
    private boolean contains(TreeNode<E> node, E value) {
        if (node == null)
            return false;
        else {
            int diff = ((Comparable<E>) value).compareTo(node.getValue());
            if (diff == 0)
                return true;
            else if (diff < 0)
                return contains(node.getLeft(), value);
            else // if (diff > 0)
                return contains(node.getRight(), value);
        }
    }

    /*
     * // Iterative version:
     * private boolean contains(TreeNode<E> node, E value)
     * {
     * while (node != null)
     * {
     * int diff = ( (Comparable<E>)value).compareTo( node.getValue() );
     * if (diff == 0)
     * return true;
     * else if (diff < 0)
     * node = node.getLeft();
     * else // if (diff > 0)
     * node = node.getRight();
     * }
     * return false;
     * }
     */

    /**
     * Adds value to the BST rooted at node. Returns the
     * root of the new tree. Precondition: the tree rooted at node does not contain
     * value.
     * 
     * @param node  The node you want to check
     * @param value The value you want to check for
     * @return Returns true if the BST rooted at node contains value;
     *         otherwise returns false (recursive version).
     */
    private TreeNode<E> add(TreeNode<E> node, E value) {
        if (node == null)
            node = new TreeNode(value);
        else {
            int diff = ((Comparable<E>) value).compareTo(node.getValue());
            if (diff < 0)
                node.setLeft(add(node.getLeft(), value));
            else // if (diff > 0)
                node.setRight(add(node.getRight(), value));
        }
        return node;
    }

    /**
     * Removes value from the BST rooted at node.
     * Returns the root of the new tree.
     * Precondition: the tree rooted at node contains value.
     * 
     * @param node  The node you want to check for
     * @param value The value you want to check
     * @return Returns the root of the new tree.
     */
    private TreeNode<E> remove(TreeNode<E> node, E value) {
        int diff = ((Comparable<E>) value).compareTo(node.getValue());
        if (diff == 0) // base case
            node = removeRoot(node);
        else if (diff < 0)
            node.setLeft(remove(node.getLeft(), value));
        else // if (diff > 0)
            node.setRight(remove(node.getRight(), value));
        return node;
    }

    /**
     * Removes the root of the BST rooted at root
     * replacing it with the smallest node from the right subtree.
     * Returns the root of the new tree.
     * 
     * @param root The root you want to remove
     * @return Returns the root of the new tree.
     */
    private TreeNode<E> removeRoot(TreeNode<E> root) {
        TreeNode<E> node = root.getRight();
        TreeNode<E> parent = root;

        if (node == null) {
            return root.getLeft();
        }
        while (node.getLeft() != null) {
            parent = node;
            node = node.getLeft();
        }

        if (parent == root) {
            parent.setRight(node.getRight());
        } else {
            parent.setLeft(node.getRight());
        }
        node.setLeft(root.getLeft());
        node.setRight(root.getRight());
        return node;
    }

    /**
     * Utility routine to print the structure of the BST
     */
    public void printSideways() {
        if (root == null)
            return;
        printSideways(root, 0);
    }

    /**
     * Precondition: original argument != null
     * 
     * @param t     The tree you want to print
     * @param depth The depth you want to print till
     */
    private void printSideways(TreeNode<E> t, int depth) {
        if (t.getRight() != null)
            printSideways(t.getRight(), depth + 1);

        process(t.getValue(), depth);

        if (t.getLeft() != null)
            printSideways(t.getLeft(), depth + 1);
    }

    /**
     * Simply display the toString version of my_data
     * 
     * @param obj   The object
     * @param depth Its depth
     */
    private void process(E obj, int depth) {
        for (int j = 1; j <= depth; j++)
            System.out.print("    ");
        System.out.println(obj.toString());
    }

    /**
     * Returns a string representation of the tree rooted at node.
     * 
     * @param node The node you want to check
     * @return Returns a string representation of the tree rooted at node.
     */
    private String toString(TreeNode<E> node) {
        if (node == null)
            return "";
        else
            return toString(node.getLeft()) + node.getValue() + ", "
                    + toString(node.getRight());
    }

    /**
     * Implements an Iterator for this tree.
     */
    private class MyTreeSetIterator implements Iterator<E> {
        private Stack<TreeNode<E>> stk;
        private TreeNode<E> tNode;

        public MyTreeSetIterator(TreeNode<E> root) {

            this.stk = new Stack<>();
            tNode = root;

            if (tNode == null) {
                return;
            }

            while (tNode != null) {
                stk.push(new TreeNode<E>(tNode.getValue(), tNode.getLeft(), tNode.getRight()));
                tNode = tNode.getLeft();
            }

        }

        public boolean hasNext() {
            return !stk.isEmpty() || tNode != null;
        }

        public E next() {
            if (stk.isEmpty()) {
                throw new NoSuchElementException();
            }
            tNode = stk.pop();
            Object obj = tNode.getValue();
            tNode = tNode.getRight();

            while (tNode != null) {
                stk.push(new TreeNode<E>(tNode.getValue(), tNode.getLeft(), tNode.getRight()));
                tNode = tNode.getLeft();
            }
            return (E) obj;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // **********a*************** main: **************************
    // good work!!!

    public static void main(String[] args) {
        String[] words = { "One", "Two", "Three", "Four", "Five",
                "Six", "Seven", "Eight", "Nine", "Ten" };
        MyTreeSet bst = new MyTreeSet();

        for (String word : words) {
            System.out.println("Added: " + word + " " + bst.add(word));
            System.out.println("Contains: " + word + " " + bst.contains(word));
            if (bst.add(word))
                System.out.println("*** Added a duplicate value ***");
            System.out.println(bst);
        }
        bst.printSideways();

        System.out.println("Traversal with an iterator:");
        for (Object obj : bst)
            System.out.print(obj + " ");
        System.out.println();

        for (String word : words) {
            System.out.println("Removed: " + word + " " + bst.remove(word));
            if (bst.remove(word))
                System.out.println("*** Removed a non-existent value ***");
            System.out.println(bst);
            bst.printSideways();
        }
    }
}