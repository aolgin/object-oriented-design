/*
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

/**
 * Class to represent a theater
 * @author Adam Olgin
 * @version 12-4-13
 */
public class Theater {
    /** The name of the theater */
    String name;
    
    /** The capacity of the theater */
    int capacity;
    
    /**
     * Constructor for a theater
     * @param name the name of the theater
     * @param capacity the max capacity
     */
    Theater(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}
