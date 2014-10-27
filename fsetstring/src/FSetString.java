/**
 * Adam Olgin
 * aolgin@ccs.neu.edu
 * 
 */


/**
 * Represents finite sets of Strings
 * @author Adam Olgin
 * @version 2013-09-13
 */
public abstract class FSetString {

    /*
     * Signature
       Public static methods:

         emptySet  :                                 ->  FSetString
         insert    : FSetString x String             ->  FSetString
         add       : FSetString x String             ->  FSetString
         size      : FSetString                      ->  int
         isEmpty   : FSetString                      ->  boolean
         contains  : FSetString x String             ->  boolean
         isSubset  : FSetString x FSetString         ->  boolean
         absent    : FSetString x String             ->  FSetString
         union     : FSetString x FSetString         ->  FSetString
         intersect : FSetString x FSetString         ->  FSetString

       Public dynamic methods (for which the receiver is an FSetString):
         toString  :                                 ->  String
         equals    :  Object                         ->  boolean
         hashCode  :                                 ->  int
     */
    
    /**
     * Creates a new empty set
     * @return a new EmptySet
     */
    public static FSetString emptySet() {
        return new EmptySet();
    }
    
    /**
     * Creates a new Insert
     * @param s1 The set to insert into
     * @param k0 The string to be inserted
     * @return a new Insert
     */
    public static FSetString insert(FSetString s1, String k0) {
        return new Insert(s1, k0);
    }
    
    /**
     * If not already present in the FSetString,
     * k0 is inserted into the set
     * @param s1 The set to add to
     * @param k0 The string to be added
     * @return FsetString with k0
     */
    public static FSetString add(FSetString s1, String k0) {
        if (!FSetString.contains(s1, k0)) {
            return FSetString.insert(s1, k0);
        }
        
        else {
            return s1;
        }
    }
    
    /**
     * Determines the size of the set
     * @param s1 The set of unknown length
     * @return the int value of the set size
     */
    public static int size(FSetString s1) {
        return s1.sizeMethod();
    }
    
    /**
     * Checks whether the set is empty
     * @param s1 The set of unknown emptiness
     * @return A boolean regarding the set's emptiness
     */
    public static boolean isEmpty(FSetString s1) {
        return s1.isEmptyMethod();
    }
    
    /**
     * Checks whether the set contains the given string
     * @param s1 The set which may or may not contain the given string
     * @param k0 The string that may or may not be in the set
     * @return A boolean regarding the membership status of k0
     */
    public static boolean contains(FSetString s1, String k0) {
        return s1.containsMethod(k0);
    }
    
    /**
     * Checks if s2 is a subset of s1
     * @param s1 The set that may have a subset of s2
     * @param s2 The set that may be a subset of s1
     * @return A boolean regarding membership status of s2 in s1
     */
    public static boolean isSubset(FSetString s1, FSetString s2) {
        return s1.isSubsetMethod(s2);
    }
    
    /**
     * Checks if k0 is absent from the Set, and if so, it inserts it
     * If present, it is removed
     * @param s1 The set to check if k0 is absent from
     * @param k0 The absent string
     * @return The set with k0
     */
    public static FSetString absent(FSetString s1, String k0) {
        return s1.absentMethod(k0);
    }
    
    /**
     * Creates a union of s1 and s1
     * @param s1 The first set to be connected to...
     * @param s2 The second set
     * @return The combined FSetString of s1 and s1
     */
    public static FSetString union(FSetString s1, FSetString s2) {
        return s1.unionMethod(s2);
    }
    
    /**
     * Creates a new FSetString with the intersecting values
     * of this set and s2
     * @param s1 The string being intersected
     * @param s2 The intersecting set
     * @return A new FSetString containing the intersection points
     */
    public static FSetString intersect(FSetString s1, FSetString s2) {
        return s1.intersectMethod(s2);
    }
    
    /**
     * Determines the size of the set
     * @return the int value of the set size
     */
    abstract int sizeMethod();
    
    /**
     * Checks whether the set is empty
     * @return A boolean regarding the set's emptiness
     */
    abstract boolean isEmptyMethod();
    
    /**
     * Checks whether the set contains the given string
     * @param k0 The string that may or may not be in the set
     * @return A boolean regarding the membership status of k0
     */
    abstract boolean containsMethod(String k0);
    
    /**
     * Checks if s2 is a subset of s1
     * @param s2 The set that may be a subset of s1
     * @return A boolean regarding membership status of s2 in s1
     */
    abstract boolean isSubsetMethod(FSetString s2);
    
    /**
     * Checks if k0 is absent from the Set, and if so, it inserts it
     * If present, it is removed
     * @param k0 The absent string
     * @return The set with k0
     */
    abstract FSetString absentMethod(String k0);
    
    /**
     * Creates a union of s1 and s1
     * @param s2 The second set
     * @return The combined FSetString of s1 and s1
     */
    abstract FSetString unionMethod(FSetString s2);
    
    /**
     * Creates a new FSetString with the intersecting values
     * of this set and s2
     * @param s2 The intersecting set
     * @return A new FSetString containing the intersection points
     */
    abstract FSetString intersectMethod(FSetString s2);
    
    /**
     * Convert this FSetString to a String
     * @return String The string version of the set
     */
    public String toString() {
        return "{...(" + FSetString.size(this) + " elements)...}"; 
    }
    
    /**
     * Is this FSetString equal to the given object?
     * @param o Object that may be equal
     * @return boolean regarding the object's equivalence
     */
    public boolean equals(Object o) {
        
        // if o is null, then it is not equal
        if (this == null) {
            return false;
        }
        
        else if (o instanceof FSetString) {
            FSetString f = (FSetString) o;
            
            // if they are subsets of each other, then they are equal
            return FSetString.isSubset(this, f) && 
                    FSetString.isSubset(f, this);
        }
        
        // if not null and not a FSetString, it is not equal
        else {
            return false;
        }
    }
    
    /**
     * What is the hashCode of this FSetString?
     * @return The int value of the hashCode
     */
    public int hashCode() {
        if (this.isEmptyMethod()) {
            return 25;
        }
        
        else {
            return this.getString().hashCode() + this.getSet().hashCode();
        }
    }
    
    /**
     * Accessor method for k
     * @return The value of k if Insert, "" if empty
     */
    abstract String getString();
    
    /** Accessor method for s1
     * @return The set
     */
    abstract FSetString getSet();
}
    
////////// EmptySet ///////////
/**
 * Sub-class of FSetString representing
 * one of its Basic Creators, EmptySet
 * @author Adam Olgin
 * @version 2013-09-13
 */
class EmptySet extends FSetString {
    
    /** Constructor for EmptySet */
    EmptySet() {
        // EmptySet contains no parameters!
    }

    /**
     * Determines the size of the set
     * @return the int value of the set size
     */
    int sizeMethod() {
        return 0;
    }

    /**
     * Checks whether the set is empty
     * @return A boolean regarding the set's emptiness
     */
    boolean isEmptyMethod() {
        return true;
    }

    /**
     * Checks whether the set contains the given string
     * @param k0 The string that may or may not be in the set
     * @return A boolean regarding the membership status of k0
     */
    boolean containsMethod(String k0) {
        return false;
    }

    /**
     * Checks if s2 is a subset of s1
     * @param s2 The set that may be a subset of s1
     * @return A boolean regarding membership status of s2 in s1
     */
    boolean isSubsetMethod(FSetString s2) {
        return true;
    }

    /**
     * Checks if k0 is absent from the Set, and if so, it inserts it
     * If present, it removes it
     * @param k0 The absent string
     * @return The set with k0
     */
    FSetString absentMethod(String k0) {
        return FSetString.emptySet();
    }

    /**
     * Creates a union of s1 and s1
     * @param s2 The second set
     * @return The combined FSetString of s1 and s1
     */
    FSetString unionMethod(FSetString s2) {
        return s2;
    }

    /**
     * Creates a new FSetString with the intersecting values
     * of this set and s2
     * @param s2 The intersecting set
     * @return A new FSetString containing the intersection points
     */
    FSetString intersectMethod(FSetString s2) {
        return FSetString.emptySet();
    }
    
    /**
     * Accessor method for k
     * k isn't available in an empty list
     * so a blank string is given
     * @return exception
     */
    String getString() {
        return "";
    }
    
    /** Accessor method for s1
     * @return This emptySet
     */
    FSetString getSet() {
        return this;
    }
}


///////// Insert //////////

/**
 * Sub-class of FSetString representing
 * one of its Basic Creators, Insert
 * @author Adam Olgin
 * @version 2013-09-13
 */
class Insert extends FSetString {
    
    /** The FSetString to be used */
    FSetString s1;
    
    /** The String to be inserted into s1 */
    String k;
    
    /** Constructor for Insert 
     * @param s1 The FSetString to be used
     * @param k The string to be inserted
     */
    Insert(FSetString s1, String k) {
        this.s1 = s1;
        this.k = k;
    }

    /**
     * Determines the size of the set
     * @return the int value of the set size
     */
    int sizeMethod() {
        if (FSetString.contains(s1, k)) {
            return FSetString.size(s1);
        }
        
        else {
            return 1 + FSetString.size(s1);
        }
    }

    /**
     * Checks whether the set is empty
     * @return A boolean regarding the set's emptiness
     */
    boolean isEmptyMethod() {
        return false;
    }

    /**
     * Checks whether the set contains the given string
     * @param k0 The string that may or may not be in the set
     * @return A boolean regarding the membership status of k0
     */
    boolean containsMethod(String k0) {
        if (k.equals((Object) k0)) {
            return true;
        }
        
        else {
            return FSetString.contains(s1, k0);
        }
    }

    /**
     * Checks if s2 is a subset of s1
     * @param s2 The set that may be a subset of s1
     * @return A boolean regarding membership status of s2 in s1
     */
    boolean isSubsetMethod(FSetString s2) {
        if (FSetString.contains(s2, k)) {
            return FSetString.isSubset(s1, s2);
        }
        
        else {
            return false;
        }
    }

    /**
     * Checks if k0 is absent from the Set, and if so, it inserts it
     * If present, it is removed
     * @param k0 The absent string
     * @return The set with k0
     */
    FSetString absentMethod(String k0) {
        if (k0.equals(k)) {
            return FSetString.absent(s1, k0);
        }
        
        else {
            return FSetString.insert(FSetString.absent(s1, k0), k);
        }
    }

    /**
     * Creates a union of s1 and s1
     * @param s2 The second set
     * @return The combined FSetString of s1 and s1
     */
    FSetString unionMethod(FSetString s2) {
        if (FSetString.contains(s2, k)) {
            return FSetString.union(s1, s2);
        }
        
        else {
            return FSetString.insert(FSetString.union(s1, s2), k);
        }
    }

    /**
     * Creates a new FSetString with the intersecting values
     * of this set and s2
     * @param s2 The intersecting set
     * @return A new FSetString containing the intersection points
     */
    FSetString intersectMethod(FSetString s2) {
        if (FSetString.contains(s2, k)) {
            return FSetString.insert(FSetString.intersect(s1, s2), k);
        }
        
        else {
            return FSetString.intersect(s1, s2);
        }
    }
    
    /**
     * Accessor method for k
     * @return The value of k
     */
    String getString() {
        return this.k;
    }
    
    /** Accessor method for s1
     * @return s1
     */
    FSetString getSet() {
        return this.s1;
    }
}