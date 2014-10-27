import java.util.Comparator;

/**
 * Class for a string comparator
 * For comparing strings by length
 * @author Adam Olgin
 * @version 2013-10-05
 *
 */
class StringByLength implements Comparator<String> {

    /**
     * Compares two strings lexigraphically
     * @param s0 the first string
     * @param s1 the second string
     * @return negative if s0 is shorter than s1
     *         positive if s1 is shorter than s0
     *         0 if they are equal lengths
     */
    public int compare(String s1, String s0) {
        return s1.length() - s0.length();
    }

    /**
     * Effect: 
     * Produces false if o is not an instance of StringByLex
     *
     * @param o the object to compare with this
     * @return whether the objects are equal
     */
    public boolean equals(Object o) {
        return o instanceof StringByLength;
    }

    /**
     * Effect: 
     * Produces an integer that is compatible 
     * with the implemented  equals method 
     * and is likely to be different 
     * for objects that are not equal.
     * @return the hashCode of the tree
     */
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    /**
     * Produce a string value of this comparator
     * @return a string version of this comparator
     */
    public String toString() {
        return "StringByLength";
    }
}

/**
 * Class for a string comparator
 * For comparing strings by lexical ordering
 * @author Adam Olgin
 * @version 2013-10-05
 */
class StringByLex implements Comparator<String> {

    /**
     * Compares two strings lexigraphically
     * @param o1 the first string
     * @param o2 the second string
     * @return negative if o1 is before o2
     *         positive if o2 is before o1
     *         0 if they are equal
     */
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

    /**
     * Effect: 
     * Produces false if o is not an instance of StringByLex
     * @param o the object to compare with this
     * @return whether the objects are equal
     */
    public boolean equals(Object o) {
        return o instanceof StringByLex;
    }

    /**
     * Effect: 
     * Produces an integer that is compatible 
     * with the implemented  equals method 
     * and is likely to be different 
     * for objects that are not equal.
     * @return the hashCode of the tree
     */
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    /**
     * Produce a string value of this comparator
     * @return a string version of this comparator
     */
    public String toString() {
        return "StringByLex";
    }

}