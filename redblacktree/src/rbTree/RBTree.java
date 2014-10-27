/*
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

package rbTree;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Abstract class to represent an in order
 * Binary Search Tree
 * @author Adam Olgin
 * @version 11-09-13
 */
public abstract class RBTree {
    
    /** The data in the node/leaf */
    String data;
    
    /** The comparator of the tree (for order) */
    Comparator<String> comp;

    /** The color of the particular node/leaf */
    String color;
    
    // COLORS
    /** Black */
    private static final String BLACK = "black";
    /** Red */
    private static final String RED = "red";
    
    // FLAGS
    /** The number of active iterators */
    int active = 0;
    
    /** Whether it is a bad iteration */
    boolean bad = false;
    
    /**
     * Constructor for BTree
     * @param data the data in the node/leaf
     * @param comp the comparator of the tree (for order)
     * @param color the color of the node
     */
    RBTree(String data, Comparator<String> comp, String color) {
        this.data = data;
        this.comp = comp;
        this.color = color;
    }
    
    /**
     * Effect: 
     * Produces false if o is not an instance of BTree.
     * Produces true if this tree and the given BTree 
     * contain the same <code>String</code>s and
     * are ordered by the same <code>Comparator</code>.
     * So if the first tree was built with Strings 
     * "hello" "bye" and "aloha" ordered
     * lexicographically,  and the second tree was built 
     * with <code>String</code>s "aloha" "hello" and "bye"  
     * and ordered lexicographically, 
     * the result would be true.
     * Does not take color into account
     *
     * @param o the object to compare with this
     * @return whether the objects are equal
     */
    public abstract boolean equals(Object o);
    
    /**
     * Effect: 
     * Produces a <code>String</code> that consists of 
     * all <code>String</code>s in this tree 
     * separated by comma and a space, 
     * generated in the order defined by this tree's 
     * <code>Comparator</code>.
     * So for a tree with <code>Strings</code> 
     * "hello" "bye" and "aloha" 
     * ordered lexicographically, 
     * the result would be "aloha, bye, hello"
     * @return string version of the btree
     */
    public abstract String toString();
    
    /**
     * Effect: 
     * Produces an integer that is compatible 
     * with the implemented  equals method 
     * and is likely to be different 
     * for objects that are not equal.
     * @return the hashCode value of this btree      
     */
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    /**
     * Factory method to generate 
     * an empty binary search tree
     * with the given <code>Comparator</code>
     * @param compT the given comparator
     * @return new empty binary search tree that uses the 
     *         given <code>Comparator</code> for ordering
     */
    RBTree binTree(Comparator<String> compT) {
        return new Leaf("", compT, BLACK);
    }
    
    /** Insert the given string into this leaf
     * Creates a new node with the data, depending on the comparator
     * @param s the string to be inserted
     * @return a new balanced rbtree with s inserted into it
     */
    abstract RBTree insert(String s);
    
    /**
     * Does this tree meet the requirements?
     * Is it's representation okay?
     * @return whether it is okay
     */
    abstract boolean repOK();
    
    /** 
     * retrieve the data from the node
     * @return the data
     */
    String getData() {
        return this.data;
    }
    
    /**
     * Access the specified child
     * "left" for left child
     * "right" for right child
     * anything else throws an error
     * @param branch the specified child
     * @return the child asked for
     */
    abstract RBTree getChild(String branch); // left or right
    
    /**
     * Does this leaf contain the given string?
     * @param s the string with questionable membership
     * @return whether s and the data are the same
     */
    abstract boolean contains(String s);
    
    /**
     * Does this leaf/node have the specified child branch?
     * "left" for left child
     * "right" for right child
     * anything else throws an error
     * @param branch the desired branch
     * @return whether the tree has it
     */
    abstract boolean hasChild(String branch);
    
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
     * Balances the tree according to four cases
     * If a red node has a red child, it will rebalance
     * @return the balanced RBTree
     */
    protected abstract RBTree balance();
    
}

/**
 * Class for representing a leaf of a RBTree
 * @author Adam Olgin
 * @version 11-09-13
 */
class Leaf extends RBTree {
    
    /** The data in the node/leaf */
    String data;
    
    /** The comparator of the tree (for order) */
    Comparator<String> comp;

    /** The color of the particular node/leaf */
    String color;

    /** 
     * Constructor for leaf
     * @param data the string value of the leaf
     * @param comp the comparator
     * @param the color of the leaf
     */
    Leaf(String data, Comparator<String> comp, String color) {
        super(data, comp, color);
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
    RBTree getChild(String branch) {
        throw new NoSuchElementException("Leaf has no child!");
    }
    
    /**
     * Effect: 
     * Produces a <code>String</code> that consists of 
     * all <code>String</code>s in this tree 
     * separated by comma and a space, 
     * generated in the order defined by this tree's 
     * <code>Comparator</code>.
     * So for a tree with <code>Strings</code> 
     * "hello" "bye" and "aloha" 
     * ordered lexicographically, 
     * the result would be "aloha, bye, hello"
     * @return string version of this leaf
     */
    public String toString() {
        return this.data;
    }

    /**
     * Effect: 
     * Produces false if o is not an instance of BTree.
     * Produces true if this tree and the given BTree 
     * contain the same <code>String</code>s and
     * are ordered by the same <code>Comparator</code>.
     * So if the first tree was built with Strings 
     * "hello" "bye" and "aloha" ordered
     * lexicographically,  and the second tree was built 
     * with <code>String</code>s "aloha" "hello" and "bye"  
     * and ordered lexicographically, 
     * the result would be true.
     * Does not take color into account
     *
     * @param o the object to compare with this
     * @return whether the objects are equal
     */
    public boolean equals(Object o) {
        if (!(o instanceof Leaf)) {
            return false;
        }
        else {
            RBTree bt = (RBTree) o;
            return this.getData().equals(bt.getData()) &&
                    this.comp.equals(bt.comp);
        }
    }

    /**
     * Does this leaf meet the criteria?
     * @return whether it is a valid BTree
     */
    boolean repOK() {
        return this != null;
    }
    
    /** Insert the given string into this leaf
     * Creates a new node with the data, depending on the comparator
     * checks for duplicates
     * @param s the string to be inserted
     * @return a new btree with s inserted into it
     */
    RBTree insert(String s) {
        if (comp.compare(s, this.data) == 0) {
            return this;
        }
        else if (comp.compare(s, this.data) < 0) { 
                // turn into a node with s in the left child
            RBTree l1 = new Leaf(s, comp, "red");
            RBTree bt = new Node(this.data, comp, this.color, 
                    l1, this.binTree(comp));
            return bt;
        }
        else {      // turn into a node with s in the right child
            RBTree l1 = new Leaf(s, comp, "red");
            RBTree bt = new Node(this.data, comp, this.color, 
                    this.binTree(comp), l1);
            return bt;
        }
    }

    /**
     * Does this leaf have a child?
     * @param branch the desired child
     * @return always false for a leaf
     */
    boolean hasChild(String branch) {
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
     * Balances the tree according to four cases
     * If a red node has a red child, it will rebalance
     * @return the balanced RBTree
     */
    protected RBTree balance() {
        return this;
    }
}

/**
 * Class for representing a node of a RBTree
 * @author Adam Olgin
 * @version 11-09-13
 */
class Node extends RBTree {
    /** The data in the node/leaf */
    String data;
    
    /** The comparator of the tree (for order) */
    Comparator<String> comp;

    /** The color of the particular node/leaf */
    String color;
    
    /** the left child of the node */
    RBTree left;
    
    /** the right child of the node */
    RBTree right;

    /** 
     * Constructor for leaf
     * @param data the string value of the leaf
     * @param comp the comparator
     * @param color the color of the node
     * @param left the left RBTree
     * @param right the right RBTree
     */
    Node(String data, Comparator<String> comp, 
            String color, RBTree left, RBTree right) {
        super(data, comp, color);
        this.left = left;
        this.right = right;
    }

    /**
     * Does this tree contain the given string?
     * Searches depending on its comparison to the data
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
     * Access the child asked for
     * using "left" and "right" for your choice
     * @param branch the branch desired
     * @return the child requested, or an error if bad input
     */
    RBTree getChild(String branch) {
        if (branch.equalsIgnoreCase("LEFT") && 
                !this.left.data.equals("")) {
            return this.left;
        }
        else if (branch.equalsIgnoreCase("RIGHT") && 
                !this.right.data.equals("")) {
            return this.right;
        }
        else {
            throw new NoSuchElementException(branch + 
                " is not one of the available options\n" +
                "Please use 'left' or 'right' instead.");
        }
    }

    /**
     * Effect: 
     * Produces a <code>String</code> that consists of 
     * all <code>String</code>s in this tree 
     * separated by comma and a space, 
     * generated in the order defined by this tree's 
     * <code>Comparator</code>.
     * So for a tree with <code>Strings</code> 
     * "hello" "bye" and "aloha" 
     * ordered lexicographically, 
     * the result would be "aloha, bye, hello"
     * @return string version of this node
     */
    public String toString() {
        return this.left.toString() + this.data + this.right.toString();
    }

    /**
     * Is this tree equal to the given object
     * by data, comparator, and structure?
     * Does not take color into account
     * @param o the object being compared to
     * @return if they are equal
     */
    public boolean equals(Object o) {
        if (!(o instanceof Node)) {
            return false;
        }
        else {
            RBTree bt = (RBTree) o;
            return this.getData().equals(bt.getData()) &&
                    this.comp.equals(bt.comp) &&
                    this.left.equals(bt.getChild("left")) &&
                    this.right.equals(bt.getChild("right"));
        }
    }

    /**
     * Does this leaf/node have the specified child branch?
     * "left" for left child
     * "right" for right child
     * anything else throws an error
     * @param branch the desired branch
     * @return whether the tree has it
     */
    boolean hasChild(String branch) {
        if (branch.equalsIgnoreCase("LEFT")) {
            return !this.left.data.equals("");
        }
        else if (branch.equalsIgnoreCase("RIGHT")) {
            return !this.right.data.equals("");
        }
        else {
            throw new NoSuchElementException(branch + 
                " is not one of the available options\n" +
                "Please use 'left' or 'right' instead.");
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
     * Does this tree meet the requirements?
     * Is it's representation okay?
     * @return whether it is okay
     */
    boolean repOK() {
        if (this == null) {
            return false;
        }
        else if (comp.compare(this.left.getData(), this.data) < 0) {
            return this.left.repOK(); // run the repOK on the left
        }
        else {
            return this.right.repOK(); // run the repOK on the right
        }
    }

    /** Insert the given string into this leaf
     * Creates a new node with the data, depending on the comparator
     * checks for duplicates
     * @param s the string to be inserted
     * @return a new btree with s inserted into it
     */
    RBTree insert(String s) {
        if (comp.compare(s, this.data) == 0 || this.data.equals("")) {
            return this;
        }
        else if (comp.compare(s, this.data) < 0) { 
                    // insert in the left child
            return this.left.insert(s);
        }
        else {      // insert in the right child
            return this.right.insert(s);
        }
    }

    /**
     * Balances the tree according to four cases
     * If a red node has a red child, it will rebalance
     * @return the balanced RBTree
     */
    protected RBTree balance() {
        String red = "red";
        String black = "black";
        
        // Case 1: B (T R (T R a x b) y c) z d
        if (this.color.equals(black) && 
                this.hasChild("left") && 
                this.left.color.equals(red) && 
                this.left.hasChild("left") && 
                this.left.getChild("left").color.equals(red)) {
            Node result = new Node(this.data, this.comp, red, 
                    this.left, this.right);
            // (T B a x b)
            result.left = this.getChild("left").getChild("left");
            // y
            result.data = this.getChild("left").data;
            // (T B c z d)
            result.right = new Node(this.data, this.comp, black, 
                    this.getChild("left").getChild("right"), this.right);
            return result;
        }
        
        // Case 2: B (T R a x (T R b y c)) z d
        else if (this.color.equals(black) && 
                this.hasChild("left") && 
                this.left.color.equals(red) && 
                this.left.hasChild("right") && 
                this.left.getChild("right").color.equals(red)) {
            Node result = new Node(this.data, this.comp, red, 
                    this.left, this.right);
            // (T B a x b)
            result.left = this.getChild("left").getChild("left");
            // y
            result.data = this.getChild("left").data;
            // (T B c z d)
            result.right = new Node(this.data, this.comp, black, 
                    this.getChild("left").getChild("right"), this.right);
            return result;
        }
        
        
        // Case 3: B a x (T R (T R b y c) z d)
        else if (this.color.equals(black) && 
                this.hasChild("right") && 
                this.right.color.equals(red) && 
                this.right.hasChild("left") && 
                this.right.getChild("left").color.equals(red)) {
            Node result = new Node(this.data, this.comp, red, 
                    this.left, this.right);
            // (T B a x b)
            result.left = this.getChild("left").getChild("left");
            // y
            result.data = this.getChild("left").data;
            // (T B c z d)
            result.right = new Node(this.data, this.comp, black, 
                    this.getChild("left").getChild("right"), this.right);
            return result;
        }
        
        // Case 4: B a x (T R b y (T R c z d))
        else {
            Node result = new Node(this.data, this.comp, red, 
                    this.left, this.right);
            // (T B a x b)
            result.left = this.getChild("left").getChild("left");
            // y
            result.data = this.getChild("left").data;
            // (T B c z d)
            result.right = new Node(this.data, this.comp, black, 
                    this.getChild("left").getChild("right"), this.right);
            return result;
        }
    }
}