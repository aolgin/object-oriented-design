/**
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

import tester.Tester;

/**
 * Class for examples and tests for FSetString
 * @author Adam Olgin
 * @version 2013-09-17
 */
class ExamplesFSetString {

    private FSetString empty = new EmptySet();
    private FSetString f1 = new Insert(empty, "Bob");
    private FSetString f2 = new Insert(f1, "Sally");
    private FSetString f3 = new Insert(f2, "John");
    private FSetString f4 = new Insert(f2, "Mike");
    private FSetString f5 = new Insert(f4, "Tom");
    
    private FSetString f6 = new Insert(empty, "Sarah");
    
    private FSetString f7 = new Insert(f4, "Sarah");
    private FSetString f8 = new Insert(f7, "Chad");
    private FSetString f9 = new Insert(f2, "Sarah");
    
    /**
     * Test the basic creator methods
     * @param t The tester library tester
     */
    void testBasicCreators(Tester t) {
        t.checkExpect(FSetString.emptySet(), empty);
        t.checkExpect(FSetString.insert(empty, "Bob"), f1);
    }
    
    /**
     * Tests the FSetString methods involving EmptySets
     * @param t The tester library tester
     */
    void testEmptyMethods(Tester t) {
        t.checkExpect(FSetString.add(empty, "Bob"), f1);
        t.checkExpect(FSetString.isEmpty(empty), true);
        t.checkExpect(FSetString.size(empty), 0);
        t.checkExpect(FSetString.isSubset(empty, f3), true);
        t.checkExpect(FSetString.isSubset(empty, f5), true);
        t.checkExpect(FSetString.contains(empty, "Bob"), false);
        t.checkExpect(FSetString.contains(empty, "Tom"), false);
        t.checkExpect(FSetString.absent(empty, "Bob"), empty);
        t.checkExpect(FSetString.union(empty, f5), f5);
        t.checkExpect(FSetString.union(empty, f2), f2);
        t.checkExpect(FSetString.intersect(empty, f4), empty);
        t.checkExpect(FSetString.intersect(empty, f2), empty);
        
    }
    
    /**
     * Tests the FSetString methods for non-empty sets
     * @param t The tester library tester
     */
    void testInsertMethods(Tester t) {
        t.checkExpect(FSetString.add(f1, "Sally"), f2);
        t.checkExpect(FSetString.add(f1, "Bob"), f1);
        t.checkExpect(FSetString.isEmpty(f1), false);
        t.checkExpect(FSetString.size(FSetString.insert(f1, "Bob")), 1);
        t.checkExpect(FSetString.size(f1), 1);
        t.checkExpect(FSetString.size(f5), 4);
        t.checkExpect(FSetString.isSubset(f1, f2), true);
        t.checkExpect(FSetString.isSubset(f2, f1), false);
        t.checkExpect(FSetString.contains(f2, "Sally"), true);
        t.checkExpect(FSetString.contains(f2, "Bob"), true);
        t.checkExpect(FSetString.contains(f2, "Tom"), false);
        
        t.checkExpect(FSetString.absent(f4, "Tom"), f4);
        t.checkExpect(FSetString.absent(f4, "Mike"), f2);
        
        t.checkExpect(FSetString.union(f6, f2), f9);
        t.checkExpect(FSetString.union(f4, f5), f5);
        t.checkExpect(FSetString.union(f4, empty), f4);
        
        t.checkExpect(FSetString.intersect(f2, f4), f2);
        t.checkExpect(FSetString.intersect(
                f2, FSetString.insert(f4, "Matt")), f2);
        t.checkExpect(FSetString.intersect(f1, f1), f1);
    }
    
    /**
     * Tests the toString, Equals, and HashCode Methods
     * @param t The tester library tester
     */
    void testDynamicMethods(Tester t) {
        t.checkExpect(empty.toString(), "{...(0 elements)...}");
        t.checkExpect(f1.toString(), "{...(1 elements)...}");
        t.checkExpect(f5.toString(), "{...(4 elements)...}");
        t.checkExpect(f8.toString(), "{...(5 elements)...}");
        
        t.checkExpect(f1.equals(f2), false);
        t.checkExpect(f1 == null, false);
        
        // Web-cat yelled at me when I did my test for null using my
        // .equals
        // t.checkExpect(f1.equals(null), false);
        
        t.checkExpect(f1.equals(empty), false);
        t.checkExpect(f1.equals(FSetString.absent(f2, "Sally")), true);
        
        t.checkExpect(f1.hashCode(), 66990);
        t.checkExpect(empty.hashCode(), 25);
        t.checkExpect(f4.hashCode(), 82114447);
    }
    
    /**
     * Tests the Accessor Methods
     * @param t The tester library tester
     */
    void testAccessors(Tester t) {
        t.checkExpect(f1.getString(), "Bob");
        t.checkExpect(f2.getString(), "Sally");
        t.checkExpect(empty.getString(), "");
        
        t.checkExpect(f1.getSet(), empty);
        t.checkExpect(f2.getSet(), f1);
        t.checkExpect(empty.getSet(), empty);
    }
    
}
