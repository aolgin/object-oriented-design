/*
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstract class to represent an in order Binary Search Tree
 * @author Adam Olgin
 * @version 11-09-13
 */
public abstract class BTree implements Iterable<String> {

    /** The data in the node/leaf */
    String data;

    /** The comparator of the tree (for order) */
    Comparator<String> comp;

    // FLAGS
    /** The number of active iterators */
    int active = 0;

    /** Whether it is a bad iteration */
    boolean bad = false;

    /**
     * Constructor for BTree
     * @param data the data in the node/leaf
     * @param comp the comparator of the tree (for order)
     */
    BTree(String data, Comparator<String> comp) {
        this.data = data;
        this.comp = comp;
    }

    /**
     * Effect: Produces false if o is not an instance of BTree. Produces true if
     * this tree and the given BTree contain the same <code>String</code>s and
     * are ordered by the same <code>Comparator</code>. So if the first tree was
     * built with Strings "hello" "bye" and "aloha" ordered lexicographically,
     * and the second tree was built with <code>String</code>s "aloha" "hello"
     * and "bye" and ordered lexicographically, the result would be true.
     * 
     * @param o the object to compare with this
     * @return whether the objects are equal
     */
    public abstract boolean equals(Object o);

    /**
     * Effect: Produces a <code>String</code> that consists of all
     * <code>String</code>s in this tree separated by comma and a space,
     * generated in the order defined by this tree's <code>Comparator</code>. So
     * for a tree with <code>Strings</code> "hello" "bye" and "aloha" ordered
     * lexicographically, the result would be "aloha, bye, hello"
     * 
     * @return string version of the btree
     */
    public abstract String toString();

    /**
     * Effect: Produces an integer that is compatible with the implemented
     * equals method and is likely to be different for objects that are not
     * equal.
     * @return the hashCode value of this btree
     */
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Insert the given string into this leaf Creates a new node with the data,
     * depending on the comparator
     * @param s the string to be inserted
     * @return a new btree with s inserted into it
     */
    abstract BTree insert(String s);

    /**
     * Does this leaf contain the given string?
     * @param s the string with questionable membership
     * @return whether s and the data are the same
     */
    abstract boolean contains(String s);

    /**
     * Factory method to generate an empty binary search tree with the given
     * <code>Comparator</code>
     * @param compT the given comparator
     * @return new empty binary search tree that uses the given
     *         <code>Comparator</code> for ordering
     */
    public static BTree binTree(Comparator<String> compT) {
        return new Leaf("", compT);
    }

    /**
     * Does this tree meet the requirements? Is it's representation okay?
     * @return whether it is okay
     */
    abstract boolean repOK();

    /**
     * retrieve the data from the node
     * @return the data
     */
    protected String getData() {
        return this.data;
    }

    /**
     * Access the specified child "left" for left child "right" for right child
     * anything else throws an error
     * @param branch the specified child
     * @return the child asked for
     */
    protected abstract BTree getChild(String branch); // left or right

    /**
     * Does this leaf/node have the specified child branch? "left" for left
     * child "right" for right child anything else throws an error
     * @param branch the desired branch
     * @return whether the tree has it
     */
    protected abstract boolean hasChild(String branch);

    /**
     * Is this BTree a leaf?
     * @return its leaf status
     */
    protected abstract boolean isLeaf();

    /**
     * Is this BTree a node?
     * @return its node status
     */
    protected abstract boolean isNode();

    /**
     * Flatten this BTree into an ArrayList of Strings
     * @return an arraylist<string> with the all the data in the tree
     */
    protected abstract ArrayList<String> flatten();

    /**
     * Creates an iterator for this BTree
     * @return a new iterator
     */
    public Iterator<String> iterator() {
        return new BTreeIterator();
    }

    /**
     * Modifies: this binary search tree by inserting the <code>String</code>s
     * from the given <code>Iterable</code> collection The tree will not have
     * any duplicates - if an item to be added equals an item that is already in
     * the tree, it will not be added.
     * 
     * @param iter the given <code>Iterable</code> collection
     */
    void build(Iterable<String> iter) {
        if (active == 0) {
            BTree bt = BTree.binTree(comp);
            for (String s : iter) {
                bt.insert(s);
            }
        }
        else {
            this.bad = true;
            throw new ConcurrentModificationException(
                    "Build with active iterator");
        }
    }

    /**
     * Modifies: this binary search tree by inserting the first numStrings
     * <code>String</code>s from the given <code>Iterable</code> collection The
     * tree will not have any duplicates - if an item to be added equals an item
     * that is already in the tree, it will not be added.
     * 
     * @param iter
     *            the given <code>Iterable</code> collection
     * @param numStrings
     *            number of <code>String</code>s to iterate through and add to
     *            BTree If numStrings is negative or larger than the number of
     *            <code>String</code>s in iter then all <code>String</code>s in
     *            iter should be inserted into the tree
     */
    void build(Iterable<String> iter, int numStrings) {
        if (active == 0) {
            Iterator<String> it = iter.iterator();
            for (int i = 0; i < numStrings && it.hasNext(); i++) {
                this.insert(it.next());
            }
        } 
        else {
            this.bad = true;
            throw new ConcurrentModificationException(
                    "Build with active iterator");
        }
    }

    /**
     * Class for BTree iterators
     * @author Adam Olgin
     * @version 11-09-13
     */
    class BTreeIterator implements Iterator<String> {

        /** The current index of the arraylist */
        int current;

        /** the flattened version of the tree */
        ArrayList<String> flattenedTree = BTree.this.flatten();

        /**
         * Constructor for the iterator
         */
        BTreeIterator() {
            this.current = 0;
            if (this.hasNext()) {
                active = active + 1;
            }
        }

        /**
         * Does this iteration have a next value?
         * @return whether it has a next or not
         */
        public boolean hasNext() {
            return (flattenedTree != null)
                    && (this.current < flattenedTree.size());
        }

        /**
         * Returns the next string in the iteration Checks if there is no next
         * or if the iteration is currently bad
         * @return the next value in the iteration
         */
        public String next() {
            if (bad) {
                throw new ConcurrentModificationException(
                        "Next on a bad iterator");
            }
            if (!this.hasNext()) {
                throw new NoSuchElementException("There is no next!");
            }
            String result = flattenedTree.get(current);
            current = current + 1;
            if (!this.hasNext()) {
                active = active - 1;
            }
            return result;
        }

        /**
         * Removes an item from the binary tree Not implemented and returns an
         * unsupported op error now
         */
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
}

/**
 * Class for representing a leaf of a BTree
 * 
 * @author Adam Olgin
 * @version 11-09-13
 */
class Leaf extends BTree {
    /** The data in the leaf */
    String data;

    /** The comparator used */
    Comparator<String> comp;

    /**
     * Constructor for leaf
     * @param data the string value of the leaf
     * @param comp the comparator
     */
    Leaf(String data, Comparator<String> comp) {
        super(data, comp);
    }

    /**
     * Does this leaf contain the given string?
     * @param s the string with questionable membership
     * @return whether s and the data are the same
     */
    boolean contains(String s) {
        return this.data.equals(s);
    }

    /**
     * Access the child of this leaf
     * @param branch the desired child ("left" or "right")
     * @return throws an exception because leaves lack children
     */
    protected BTree getChild(String branch) {
        throw new NoSuchElementException("Leaf has no child!");
    }

    /**
     * Effect: Produces a <code>String</code> that consists of all
     * <code>String</code>s in this tree separated by comma and a space,
     * generated in the order defined by this tree's <code>Comparator</code>. So
     * for a tree with <code>Strings</code> "hello" "bye" and "aloha" ordered
     * lexicographically, the result would be "aloha, bye, hello"
     * 
     * @return string version of this leaf
     */
    public String toString() {
        return this.data;
    }

    /**
     * Effect: Produces false if o is not an instance of BTree. Produces true if
     * this tree and the given BTree contain the same <code>String</code>s and
     * are ordered by the same <code>Comparator</code>. So if the first tree was
     * built with Strings "hello" "bye" and "aloha" ordered lexicographically,
     * and the second tree was built with <code>String</code>s "aloha" "hello"
     * and "bye" and ordered lexicographically, the result would be true.
     * 
     * @param o the object to compare with this
     * @return whether the objects are equal
     */
    public boolean equals(Object o) {
        if (!(o instanceof Leaf)) {
            return false;
        } 
        else {
            BTree bt = (BTree) o;
            return this.getData().equals(bt.getData())
                    && this.comp.equals(bt.comp);
        }
    }

    /**
     * Does this leaf meet the criteria?
     * @return whether it is a valid BTree
     */
    boolean repOK() {
        return this != null;
    }

    /**
     * Insert the given string into this leaf Creates a new node with the data,
     * depending on the comparator checks for duplicates
     * @param s the string to be inserted
     * @return a new btree with s inserted into it
     */
    BTree insert(String s) {
        if (comp.compare(s, this.data) == 0 || this.data.equals("") || this.data == null) {
            return new Leaf(s, this.comp);
        }
        else if (comp.compare(s, this.data) < 0) {
            // turn into a node with s in the left child
            BTree l1 = new Leaf(s, comp);
            BTree bt = new Node(this.data, comp, l1, BTree.binTree(comp));
            return bt;
        }
        else { // turn into a node with s in the right child
            BTree l1 = new Leaf(s, comp);
            BTree bt = new Node(this.data, comp, BTree.binTree(comp), l1);
            return bt;
        }
    }

    /**
     * Does this leaf have a child?
     * @param branch the desired child
     * @return always false for a leaf
     */
    protected boolean hasChild(String branch) {
        return false;
    }

    /**
     * Is this leaf a leaf?
     * @return always true for a leaf
     */
    protected boolean isLeaf() {
        // may need to put a check in to see if data = ""
        return true;
    }

    /**
     * Is this leaf a node?
     * @return always false for a leaf
     */
    protected boolean isNode() {
        return false;
    }

    /**
     * Add this leaf's data into an ArrayList
     * @return a flattened tree
     */
    protected ArrayList<String> flatten() {
        ArrayList<String> result = new ArrayList<String>();

        result.add(data);
        return result;
    }
}

/**
 * Class for representing a node of a BTree
 * @author Adam Olgin
 * @version 11-09-13
 */
class Node extends BTree {
    /** The data in the node/leaf */
    String data;

    /** The comparator of the tree (for order) */
    Comparator<String> comp;

    /** the left child of the node */
    BTree left;

    /** the right child of the node */
    BTree right;

    /**
     * Constructor for leaf
     * @param data the string value of the leaf
     * @param comp the comparator
     * @param left the left BTree
     * @param right the right BTree
     */
    Node(String data, Comparator<String> comp, BTree left, BTree right) {
        super(data, comp);
        this.left = left;
        this.right = right;
    }

    /**
     * Does this tree contain the given string? Searches depending on its
     * comparison to the data
     * @param s the string that may be in the tree
     * @return whether it is in the tree
     */
    boolean contains(String s) {
        if (this.data.equals(s)) {
            return true;
        }
        // see if the left contains it
        else if (comp.compare(s, this.data) < 0) {
            return this.left.contains(s);
        }
        // see if the right contains it
        else {
            return this.right.contains(s);
        }
    }

    /**
     * Access the child asked for using "left" and "right" for your choice
     * @param branch the branch desired
     * @return the child requested, or an error if bad input
     */
    protected BTree getChild(String branch) {
        if (branch.equalsIgnoreCase("left") && !this.left.data.equals("")) {
            return this.left;
        }
        else if (branch.equalsIgnoreCase("right")
                && !this.right.data.equals("")) {
            return this.right;
        }
        else {
            throw new NoSuchElementException(branch
                    + " is not one of the available options\n"
                    + "Please use 'left' or 'right' instead.");
        }
    }

    /**
     * Effect: Produces a <code>String</code> that consists of all
     * <code>String</code>s in this tree separated by comma and a space,
     * generated in the order defined by this tree's <code>Comparator</code>. So
     * for a tree with <code>Strings</code> "hello" "bye" and "aloha" ordered
     * lexicographically, the result would be "aloha, bye, hello"
     * @return string version of this node
     */
    public String toString() {
        return this.left.toString() + ", " + this.data + ", "
                + this.right.toString();
    }

    /**
     * Effect: Produces false if o is not an instance of BTree. Produces true if
     * this tree and the given BTree contain the same <code>String</code>s and
     * are ordered by the same <code>Comparator</code>. So if the first tree was
     * built with Strings "hello" "bye" and "aloha" ordered lexicographically,
     * and the second tree was built with <code>String</code>s "aloha" "hello"
     * and "bye" and ordered lexicographically, the result would be true.
     * 
     * @param o the object to compare with this
     * @return whether the objects are equal
     */
    public boolean equals(Object o) {
        if (!(o instanceof Node)) {
            return false;
        } 
        else {
            BTree bt = (BTree) o;
            return this.getData().equals(bt.getData())
                    && this.comp.equals(bt.comp)
                    && this.left.equals(bt.getChild("left"))
                    && this.right.equals(bt.getChild("right"));
        }
    }

    /**
     * Does this leaf/node have the specified child branch? "left" for left
     * child "right" for right child anything else throws an error
     * @param branch the desired branch
     * @return whether the tree has it
     */
    protected boolean hasChild(String branch) {
        if (branch.toUpperCase().equals("LEFT")) {
            return !this.left.data.equals("");
        } 
        else if (branch.toUpperCase().equals("RIGHT")) {
            return !this.right.data.equals("");
        }
        else {
            throw new RuntimeException(branch
                    + " is not one of the available options\n"
                    + "Please use 'left' or 'right' instead.");
        }
    }

    /**
     * Is this BTree a leaf?
     * @return its leaf status
     */
    protected boolean isLeaf() {
        return false;
    }

    /**
     * Is this BTree a node?
     * @return its node status
     */
    protected boolean isNode() {
        return true;
    }

    /**
     * Does this tree meet the requirements? Is it's representation okay?
     * @return whether it is okay
     */
    boolean repOK() {
        if (this == null) {
            return false;
        } 
        else if (comp.compare(this.left.getData(), this.data) < 0) {
            return this.left.repOK(); // run the repOk on the left
        } 
        else {
            return this.right.repOK(); // run the repOk on the right
        }
    }

    /**
     * Insert the given string into this leaf Creates a new node with the data,
     * depending on the comparator Checks for duplicates
     * @param s the string to be inserted
     * @return a new btree with s inserted into it
     */
    BTree insert(String s) {
        if (comp.compare(s, this.data) == 0) {
            return this;
        } 
        else if (comp.compare(s, this.data) < 0) {
            // insert in the left child
            return this.left.insert(s);
        } 
        else { // insert in the right child
            return this.right.insert(s);
        }
    }

    /**
     * Flattens out the binary tree to an arraylist
     * @return an arrayList version of the binary tree
     */
    public ArrayList<String> flatten() {
        ArrayList<String> flattened = new ArrayList<String>();

        if (this.left.isLeaf()) {
            flattened.add(data);
            flattened.addAll(this.right.flatten());
        } 
        else if (this.right.isLeaf()) {
            flattened.addAll(this.left.flatten());
            flattened.add(data);
        } 
        else {
            flattened.addAll(this.left.flatten());
            flattened.add(data);
            flattened.addAll(this.right.flatten());
        }
        return flattened;
    }
}