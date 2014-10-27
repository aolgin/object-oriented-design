/**
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

/** class for representing a list of integers 
 * @author Adam Olgin
 * @version 09-24-13
 */
public abstract class FListInteger {
    /** 
     * create a new empty list 
     * @return a new emptyList
     */
    
    public static FListInteger emptyList() {
        return new EmptyList();
    }
    
    /** 
     * create a new list with at least one element
     * @param f the list that is being added to
     * @param x the integer being added to the list
     * @return the new list with x added in
     */
    public static FListInteger add(FListInteger f, Integer x) {
        return new Add(f, x);
    }
    
    /**
     * Is the list empty?
     * @param f the list of unknown emptiness
     * @return true if empty, false if not
     */
    public static boolean isEmpty(FListInteger f) {
        return f.isEmptyMethod();
    }
    
    /**
     * Get the value of the integer at the given location
     * @param f the list to be searched
     * @param n the position of the integer
     * @return the integer value at the nth position
     */
    public static Integer get(FListInteger f, int n) {
        return f.getMethod(n);
    }
    
    /**
     * Sets the nth value in the list to the given Integer
     * @param f the list to be altered
     * @param n the position to set at
     * @param x the Integer to set the nth value to
     * @return the list with the value set as desired
     */
    public static FListInteger set(FListInteger f, int n, Integer x) {
        return f.setMethod(n, x);
    }
    
    /**
     * How many items are in the list?
     * @param f the list of unknown length
     * @return the int value of the size
     */
    public static int size(FListInteger f) {
        return f.sizeMethod();
    }
    
    /**
     * Is the list empty?
     * @return true if empty, false if not
     */
    abstract boolean isEmptyMethod();
    
    /**
     * Get the value of the integer at the given location
     * @param n the position of the integer
     * @return the integer value at the nth position
     */
    abstract Integer getMethod(int n);
    
    /**
     * Sets the nth value in the list to the given Integer
     * @param n the position to set at
     * @param y the Integer to set the nth value to
     * @return the list with the value set as desired
     */
    abstract FListInteger setMethod(int n, Integer y);
    
    /**
     * How many items are in the list?
     * @return the int value of the size
     */
    abstract int sizeMethod();
    
    /**
     * Get the value of the integer being added
     * throw error if on an empty list
     * @return the integer value of the element being added
     */    
    abstract Integer getInt();

    /**
     * Get the value of the list being added to
     * @return the list being added to
     */    
    abstract FListInteger getList();

    /**
     * Convert this list into a string of its contents
     * @return a string version of the list
     */
    public String toString() {
        if (FListInteger.isEmpty(this)) {
            return "[]";
        }
        else if (FListInteger.isEmpty(this.getList())) {
            return "[" + this.getInt() + "]";
        }
        else {
            return "[" + this.getInt() +
                   ", " + this.getList().toString().substring(
                                1, this.getList().toString().length());
        }
    }
    
    /**
     * Calculate the hashCode of this list
     * @return the int value of the hashCode of the object
     */
    public int hashCode() {
        if (FListInteger.isEmpty(this)) {
            return 13;
        }
        else {
            return this.getInt().hashCode() + this.getList().hashCode();
        }
    }
    
    /**
     * Is this list equal to the given object?
     * Are the contents the same and in the right order?
     * @param o an object potentially equal
     * @return the state of equality between the items
     */
    public abstract boolean equals(Object o);
    

}

/**
 * Representing an empty list
 * @author Adam Olgin
 * @version 09-24-13
 */
class EmptyList extends FListInteger {
    
    /** Constructor for an empty list */
    EmptyList() {
    // an empty constructor
    }

    /**
     * Is the list empty?
     * @return true if empty, false if not
     */
    boolean isEmptyMethod() {
        return true;
    }

    /**
     * Get the value of the integer at the given location
     * @param n the position of the integer
     * @return the integer value at the nth position
     */
    Integer getMethod(int n) {
        String msg1 = "The list is empty! No value to get here!";
        throw new RuntimeException(msg1);
    }

    /**
     * Sets the nth value in the list to the given Integer
     * @param n the position to set at
     * @param y the Integer to set the nth value to
     * @return the list with the value set as desired
     *         when empty, returns the empty list
     */
    FListInteger setMethod(int n, Integer y) {
        return this;
    }

    /**
     * How many items are in the list?
     * @return the int value of the size
     */
    int sizeMethod() {
        return 0;
    }

    /**
     * Get the value of the integer being added
     * throw error if on an empty list
     * @return the integer value of the element being added
     */
    Integer getInt() {
        String msg1 = "No int in an empty list!";
        throw new RuntimeException(msg1);
    }

    /**
     * Get the value of the list being added to
     * @return the list being added to
     */
    FListInteger getList() {
        return this;
    }

    /**
     * Is this list equal to the given object?
     * Are the contents the same and in the right order?
     * @param o an object potentially equal
     * @return the state of equality between the items
     */
    public boolean equals(Object o) {
        if (o == null)  {
            return false;
        }
        
        if (o instanceof FListInteger) {
            FListInteger f2 = (FListInteger) o;
            
            return FListInteger.isEmpty(f2);
        }
        
        else {
            return false;
        }
    }
}

/**
 * Representing a list with at least one value
 * @author Adam Olgin
 * @version 09-24-13
 */
class Add extends FListInteger {
    
    /** The list being added to */
    FListInteger f;
    
    /** The Integer being added */
    Integer x;
    
    /** 
     * The constructor for the Add class
     * @param f the list being added to
     * @param x the Integer being add
     */
    Add(FListInteger f, Integer x) {
        this.f = f;
        this.x = x;
    }

    /**
     * Is the list empty?
     * @return true if empty, false if not
     */
    boolean isEmptyMethod() {
        return false;
    }

    /**
     * Get the value of the integer at the given location
     * @param n the position of the integer
     * @return the integer value at the nth position
     */
    Integer getMethod(int n) {
        if (n == 0) {
            return x;
        }
        else {
            return FListInteger.get(f, n - 1);
        }
    }

    /**
     * Sets the nth value in the list to the given Integer
     * @param n the position to set at
     * @param y the Integer to set the nth value to
     * @return the list with the value set as desired
     */
    FListInteger setMethod(int n, Integer y) {
        if (n == 0) {
            return FListInteger.add(f, y);
        }
        else {
            return FListInteger.add(FListInteger.set(f, n - 1, y), x);
        }
    }

    /**
     * How many items are in the list?
     * @return the int value of the size
     */
    int sizeMethod() {
        return 1 + FListInteger.size(f);
    }

    /**
     * Get the value of the integer being added
     * throw error if on an empty list
     * @return the integer value of the element being added
     */
    Integer getInt() {
        return this.x;
    }

    /**
     * Get the value of the list being added to
     * @return the list being added to
     */
    FListInteger getList() {
        return this.f;
    }

    /**
     * Is this list equal to the given object?
     * Are the contents the same and in the right order?
     * @param o an object potentially equal
     * @return the state of equality between the items
     */
    public boolean equals(Object o) {
        if (o == null)  {
            return false;
        }
        
        if (o instanceof FListInteger) {
            FListInteger f2 = (FListInteger) o;
            
            if (FListInteger.size(this) == FListInteger.size(f2)) {
                for (int i = 0; i < FListInteger.size(this); i++) {
                    if (!FListInteger.get(this, i).equals(
                            FListInteger.get(f2, i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}

