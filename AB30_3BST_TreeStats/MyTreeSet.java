import java.util.Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a BST with TreeNode nodes.
 * 
 * @author Shivam Maji
 * @version 2/2/22
 * 
 * @author Period - 6
 * @author Assignment - AB30.3 - TreeStats
 * 
 * @author Sources - Me Myself and I
 */
public class MyTreeSet<E> {
    private TreeNode<E> myRoot; // holds the root of this BST

    /**
     * Constructor: creates an empty BST.
     */
    public MyTreeSet() {
        myRoot = null;
    }

    /**
     * post: prints the data fields of the tree - print out on a single line with a
     * character and space - ex. A C E G H
     */
    public void printInorder() {
        printInorderHelper(myRoot);
    }

    /**
     * pre : root points to a binary search tree post: prints the data fields of the
     * tree using an inorder traversal
     * 
     * @param root The root you want to point to
     */
    private void printInorderHelper(TreeNode<E> root) {

        if (root != null) {
            printInorderHelper(root.getLeft());
            System.out.print(root.getValue() + " ");
            printInorderHelper(root.getRight());
        }
    }

    /**
     * post: prints the data fields of the tree - print out on a single line with a
     * character and space - ex. A C E G H
     */
    public void printPreorder() {
        printPreorderHelper(myRoot);
    }

    /**
     * pre : root points to a binary search tree
     * post: prints the data fields of the tree using an inorder traversal
     * 
     * @param root The root you want to look at
     */
    private void printPreorderHelper(TreeNode<E> root) {
        if (root != null) {
            System.out.print(root.getValue() + " ");
            printPreorderHelper(root.getLeft());
            printPreorderHelper(root.getRight());
        }
    }

    /**
     * post: prints the data fields of the tree - print out on a single line
     * with a character and space - ex. A C E G H
     */
    public void printPostorder() {
        printPostorderHelper(myRoot);
    }

    /**
     * pre : root points to a binary search tree
     * post: prints the data fields of the tree using an inorder traversal
     * 
     * @param root
     */
    private void printPostorderHelper(TreeNode<E> root) {
        if (root != null) {
            printPostorderHelper(root.getLeft());
            printPostorderHelper(root.getRight());
            System.out.print(root.getValue() + " ");
        }
    }

    /**
     * post: returns the number of nodes in the tree
     * 
     * @return number of nodes
     */
    public int countNodes() {
        return countNodesHelper(myRoot);
    }

    /**
     * pre : root points to a binary tree
     * post: returns the number of nodes
     * 
     * @param root the root you want to check
     * @return the number of nodes
     */
    private int countNodesHelper(TreeNode<E> root) {

        if (root == null) {
            return 0;
        } else {
            return countNodesHelper(root.getLeft()) + countNodesHelper(root.getRight()) + 1;
        }
    }

    /**
     * post: returns the number of leaves in the tree
     * 
     * @return the number of leaves
     */
    public int countLeaves() {
        return countLeaves(myRoot);
    }

    /**
     * pre : root points to a binary tree
     * // post: returns the number of leaves
     * 
     * @param root The root you want to check
     * @return
     */
    private int countLeaves(TreeNode<E> root) {

        if (root == null) {
            return 0;
        } else if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        } else {
            return countLeaves(root.getLeft()) + countLeaves(root.getRight());
        }
    }

    /**
     * post: returns the number of nodes in the longest path from the root
     * to a leaf of the tree
     * 
     * @return the number of nodes in the longest path from the root
     *         to a leaf of the tree
     */
    public int height() {
        return height(myRoot);
    }

    /**
     * pre : root points to a binary tree
     * post: returns the number of nodes in the longest path from the root
     * to a leaf of the tree
     * 
     * @param root The root you want to check
     * @return
     */
    private int height(TreeNode<E> root) {

        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.getLeft()), height(root.getRight())) + 1;
        }

    }

    // post: returns the number of nodes in the longest path in the tree
    public int width() {
        return width(myRoot);
    }

    // pre : root points to a binary tree
    // post: returns the number of nodes in the longest path in the tree
    private int width(TreeNode<E> root) {

        if (root == null) {
            return 0;
        } else if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        } else {
            return Math.max(height(root.getLeft()) + height(root.getRight()) + 1,
                    Math.max(width(root.getLeft()), width(root.getRight())));
        }

    }

    // helper for height and width methods.
    // post: return the largest of a and b
    private int max(int a, int b) {
        if (a > b)
            return a;
        else
            return b;
    }

    // post: root of this tree is set to null
    public void clearTree() {

        myRoot = null;
    }

    // post: root points to a tree that is a mirror image of the original tree.
    public void interchange() {
        interchange(myRoot);
    }

    // pre : root points to a binary tree
    // post: root points to a tree that is a mirror image of the original tree.
    private void interchange(TreeNode<E> root) {

        if (root != null) {
            TreeNode<E> alhamdulilah = root.getRight();
            root.setRight(root.getLeft());
            root.setLeft(alhamdulilah);
            interchange(root.getLeft());
            interchange(root.getRight());
        }
    }

    // post: prints the data fields of nodes on the same level
    // of the tree
    // NOTE: The root is considered level 0
    public void printLevel(int level) {
        printLevel(myRoot, level);
    }

    // pre : root points to a binary tree
    // post: prints the data fields of nodes on the same level
    // of the tree - print out on a single line - ex. A C E B
    private void printLevel(TreeNode<E> root, int level) {
        if (root == null) {
            return;
        }
        if (root != null && level == 0) {
            System.out.print(root.getValue() + " ");
        } else {
            printLevel(root.getLeft(), level - 1);
            printLevel(root.getRight(), level - 1);
        }

    }

    // post: returns true if ancestor is the ancestor of descendant
    // else returns false
    // The ancestor/descendant is a of type <E> (i.e. the node's getValue()
    public boolean isAncestor(Comparable ancestor, Comparable descendant) {
        return isAncestor(myRoot, ancestor, descendant);
    }

    // pre : root points to a binary search tree
    // post: returns true if ancestor is the ancestor of descendant
    // else returns false
    private boolean isAncestor(TreeNode<E> root, Comparable a, Comparable d) {
        // find if descendant is in subtree with root at ancestor
        if (findPtr(findPtr(root, a), d) != null)
            return true;
        else
            return false;
    }

    // pre : root points to a binary search tree
    // post: returns the value of a node with data equal to target or null
    // if not found
    private TreeNode<E> findPtr(TreeNode<E> root, Comparable target) {

        if (root == null) {
            return null;
        } else if (target.equals(root.getValue())) {
            return root;
        }
        TreeNode<E> up = findPtr(root.getLeft(), target);
        TreeNode<E> dog = findPtr(root.getRight(), target);
        if (up != null) {
            return up;
        } else if (dog != null) {
            return dog;
        } else {
            return null;
        }

    }

    public int[] nodeCounts() {
        return nodeCounts(myRoot);
    }

    // Exercise 21
    // Write a method, nodeCounts, that returns an array of two elements:
    // the first is set to the number of nodes in the tree rooted at root;
    // the second is the difference between the node counts for the left
    // and right subtrees of root. For an empty tree, both values should
    // be set to 0.
    private int[] nodeCounts(TreeNode<E> root) {
        if (root == null) {
            return new int[] { 0, 0 };
        } else {
            int left = nodeCounts(root.getLeft())[0];
            int right = nodeCounts(root.getRight())[0];
            return new int[] { left + right + 1, left - right };
        }
    }

    public void accumulate() {
        accumulate((TreeNode<Integer>) myRoot);
    }

    // Exercise 22
    // Replaces the value in each node with the sum of the values in all the
    // nodes of its subtree (inclusive of the value of rooot).
    // Precondition: the nodes of the tree rooted at root hold Integer values.
    // Ex. 3 13
    // 4 6 --> 4 6
    private void accumulate(TreeNode<Integer> root) {
        if (root != null && !(root.getLeft() == null && root.getRight() == null)) {
            accumulate(root.getLeft());
            accumulate(root.getRight());
            root.setValue(root.getValue() + root.getLeft().getValue() + root.getRight().getValue());
        }
    }

    public TreeNode<E> trim() {
        return trim(myRoot);
    }

    // Exercise 23
    // Write a trim method that removes all the leaves from the tree rooted
    // at root and returns a reference to the root of the modified tree
    // (or null, if the original tree is empty or has only one node).
    private TreeNode<E> trim(TreeNode<E> root) {
        if (root == null || (root.getRight() == null && root.getRight() == null)) {
            return root;
        } else {
            if (trim(root.getLeft()) != null) {
                root.setLeft(null);
            }
            if (trim(root.getRight()) != null) {
                root.setRight(null);
            }
            return null;
        }

    }

    public double minMaxAverage() {
        return minMaxAverage((TreeNode<Integer>) myRoot);
    }

    // Exercise 24
    // Write a method, minMaxAverage, that returns the average of the
    // smallest and the largest value in a binary search tree rooted at
    // root (assuming the nodes of the tree hold Integer values).
    private double minMaxAverage(TreeNode<Integer> root) {

        if (root == null) {
            return 0;
        } else {
            TreeNode<Integer> min = root.getLeft();
            while (min.getLeft() != null) {
                min = min.getLeft();
            }
            TreeNode<Integer> max = root.getRight();
            while (max.getRight() != null) {
                max = max.getRight();
            }
            return (min.getValue() + max.getValue()) / 2.0;
        }

    }

    // *************** non-exercise methods: *********************

    // Returns true if this BST contains value; otherwise returns false.
    public boolean contains(E value) {
        return contains(myRoot, value);
    }

    // Adds value to this BST, unless this tree already holds value.
    // Returns true if value has been added; otherwise returns false.
    public boolean add(E value) {
        if (contains(value))
            return false;
        myRoot = add(myRoot, value);
        return true;
    }

    // Returns a string representation of this BST.
    public String toString() {
        String str = toString(myRoot);
        if (str.endsWith(", "))
            str = str.substring(0, str.length() - 2);
        return "[" + str + "]";
    }

    // *************** Private helper methods: *********************

    // Returns true if the BST rooted at node contains value;
    // otherwise returns false (recursive version).
    private boolean contains(TreeNode<E> node, E value) {
        if (node == null)
            return false;
        else {
            int diff = ((Comparable<E>) value).compareTo(node.getValue());
            if (diff == 0)
                return true;
            else if (diff < 0)
                return contains(node.getLeft(), value);
            else
                // if (diff > 0)
                return contains(node.getRight(), value);
        }
    }

    // Adds value to the BST rooted at node. Returns the
    // root of the new tree.
    // Precondition: the tree rooted at node does not contain value.
    private TreeNode<E> add(TreeNode<E> node, E value) {
        if (node == null)
            node = new TreeNode<E>(value);
        else {
            int diff = ((Comparable<E>) value).compareTo((E) node.getValue());
            if (diff < 0)
                node.setLeft(add(node.getLeft(), value));
            else // if (diff > 0)
                node.setRight(add(node.getRight(), value));
        }
        return node;
    }

    public void printSideways() {
        if (myRoot == null)
            return;
        printSideways(myRoot, 0);
    }

    // Precondition: original argument != null
    private void printSideways(TreeNode<E> t, int depth) {
        if (t.getRight() != null)
            printSideways(t.getRight(), depth + 1);

        process((E) t.getValue(), depth);

        if (t.getLeft() != null)
            printSideways(t.getLeft(), depth + 1);
    }

    // Simply display the toString version of my_data
    private void process(E obj, int depth) {
        for (int j = 1; j <= depth; j++)
            System.out.print("    ");
        System.out.println(obj.toString());
    }

    // Returns a string representation of the tree rooted at node.
    private String toString(TreeNode<E> node) {
        if (node == null)
            return "";
        else
            return toString(node.getLeft()) + node.getValue() + ", "
                    + toString(node.getRight());
    }
}