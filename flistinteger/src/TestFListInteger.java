/**
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */


/**
 * Basic test program for Assignment2 - FListInteger
 * @author Adam Olgin
 * @version 2013-09-21
 */
public class TestFListInteger {

    /**
     * Runs the tests
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestFListInteger test = new TestFListInteger();
        
        System.out.println("Testing 0-argument emptySet()");
        test.creation();
        test.accessors(0);
        test.usual();
        test.accessors(0);
        test.usual();
        
        test.summarize();
    }
    
    /** Prints a summary of the tests */
    private void summarize() {
        System.out.println();
        System.out.println(totalErrors + " errors found in "
            + totalTests + " tests.");
    }
    
    /** Constructor for TestFListInteger */
    public TestFListInteger() { 
        // an empty constructor for this test class
    }
    
    // Integer objects to serve as elements
    private final Integer one = 1;
    private final Integer two = 2;
    private final Integer three = 3;
    private final Integer four = 4;

    // FListInteger objects to be created and then tested
    
    private FListInteger f0;   // []
    private FListInteger f1;   // [1]
    private FListInteger f2;   // [2, 1]
    private FListInteger f3;   // [3, 2, 1]
    private FListInteger f4;   // [4, 3, 2, 1]
    private FListInteger f5;   // [1, 2, 1]
    private FListInteger f6;   // [3, 4, 2, 1]
    private FListInteger f7;   // [1, 2, 2, 1]

    private FListInteger s1;
    private FListInteger s2;   // [2]
    private FListInteger s3;   // [3]
    private FListInteger s4;
    private FListInteger s5;
    
    private FListInteger s23;  // [2, 3]
    private FListInteger s324; // [3, 2, 4]
    
    /** Creates some FListInteger objects */
    private void creation() {
        try {
            f0 = FListInteger.emptyList();
            f1 = FListInteger.add(f0, one);
            f2 = FListInteger.add(f1, two);
            f3 = FListInteger.add(f2, three);
            f4 = FListInteger.add(f3, four);
            f5 = FListInteger.add(f2, one);
            f6 = FListInteger.add(FListInteger.add(f2, four), three);
            
            f7 = FListInteger.add(f0, one);
            f7 = FListInteger.add(f7, two);
            f7 = FListInteger.add(f7, two);
            f7 = FListInteger.add(f7, one);
            
            s1 = FListInteger.add(f0, 1);
            s2 = FListInteger.add(f0, two);
            s3 = FListInteger.add(f0, three);
            s4 = f2;
            
            s5 = FListInteger.add(f0, 1);
            s5 = FListInteger.add(s5, 2);
            s5 = FListInteger.add(s5, 4);
            s5 = FListInteger.add(s5, 1);
            
            
            
            s23 = FListInteger.add(s3, two);
            
            s324 = FListInteger.add(f0, four);
            s324 = FListInteger.add(s324, two);
            s324 = FListInteger.add(s324, three);
        }
        catch (Exception e) {
            System.out.println("Exception thrown during creation tests:");
            System.out.println(e);
            assertTrue("creation", false);
        }
    }
    
    /** Test the accessors */
    private void accessors(int nargs) {
        try {
            //testing isEmpty
            assertTrue("empty", FListInteger.isEmpty(f0));
            assertFalse("nonempty", FListInteger.isEmpty(f1));
            assertFalse("nonempty", FListInteger.isEmpty(f3));
            
            //testing size
            assertTrue("f0.size()", FListInteger.size(f0) == 0);
            assertTrue("f1.size()", FListInteger.size(f1) == 1);
            assertTrue("f2.size()", FListInteger.size(f2) == 2);
            assertTrue("f3.size()", FListInteger.size(f3) == 3);
            assertTrue("f4.size()", FListInteger.size(f4) == 4);
            assertTrue("f5.size()", FListInteger.size(f5) == 3);
            assertTrue("f7.size()", FListInteger.size(f7) == 4);
            
            //testing get
            assertTrue("f1.get(0)", FListInteger.get(f1, 0) == 1);
            assertTrue("f2.get(0)", FListInteger.get(f2, 0) == 2);
            assertTrue("f2.get(1)", FListInteger.get(f2, 1) == 1);
            assertTrue("f7.get(0)", FListInteger.get(f7, 0) == 1);
            assertTrue("f7.get(1)", FListInteger.get(f7, 1) == 2);
            assertTrue("f7.get(2)", FListInteger.get(f7, 2) == 2);
            assertTrue("f7.get(3)", FListInteger.get(f7, 3) == 1);
            assertTrue("s3.get(0)", FListInteger.get(s3, 0) == 3);
            
            //testing set
            assertTrue("f1.set(0, 2)", FListInteger.set(f1, 0, 2).equals(s2));
            assertTrue("f2.set(1, 3)", FListInteger.set(f2, 1, 3).equals(s23));
            assertTrue("f3.set(2, 3)", FListInteger.set(f2, 2, 3).equals(s23));
            assertTrue("s3.set(0, 2)", FListInteger.set(s3, 0, 2).equals(s2));
        }
        catch (Exception e) {
            System.out.println("Exception thrown during accessors tests:");
            System.out.println(e);
            assertTrue("accessors", false);
        }
    }

    /** Test the usual overridden methods */
    private void usual() {
        try {
            //testing toString
            assertTrue("toString0",
                    f0.toString().equals("[]"));
            assertTrue("toString1",
                    f1.toString().equals("[1]"));
            assertTrue("toString7",
                    f7.toString().equals("[1, 2, 2, 1]"));

            //testing equals
            assertTrue("equals00", f0.equals(f0));
            assertTrue("equals33", f3.equals(f3));
            assertTrue("equals55", f5.equals(f5));
            assertTrue("equals11", f1.equals(s1));
            assertTrue("equals112", s1.equals(f1));
            
            assertTrue("equals18", f2.equals(s4));
            assertTrue("equals81", s4.equals(f2));
            
            assertFalse("equals75", f7.equals(s5));
            assertFalse("equals57", s5.equals(f7));
            
            assertFalse("equals46", f4.equals(f6));
            assertFalse("equals64", f6.equals(f4));
            assertFalse("equals27", f2.equals(f7));
            assertFalse("equals72", f7.equals(f2));
            assertFalse("equals01", f0.equals(f1));
            assertFalse("equals02", f0.equals(f2));
            assertFalse("equals10", f1.equals(f0));
            assertFalse("equals12", f1.equals(f2));
            assertFalse("equals21", f2.equals(f1));
            assertFalse("equals23", f2.equals(f3));
            assertFalse("equals35", f3.equals(f5));

            assertFalse("equals0string", f0.equals("just a string"));
            assertFalse("equals4string", f4.equals("just a string"));

            assertFalse("equals0null", f0 == null);
            assertFalse("equals1null", f1 == null);
            // WebCat would not accept the tests as written below
            // assertFalse("equals0null", f0.equals(null);
            // assertFalse("equals1null", f1.equals(null);

            //testing hashCode
            assertTrue("hashCode00", f0.hashCode() == f0.hashCode());
            assertTrue("hashCode44", f4.hashCode() == f4.hashCode());
            assertFalse("hashCode24", f2.hashCode() == f4.hashCode());
        }
        catch (Exception e) {
            System.out.println("Exception thrown during usual tests:");
            System.out.println(e);
            assertTrue("usual", false);
        }
    }

//////////////////////////////////////////////////////////////////////////
    
    private int totalTests = 0; // tests run so far
    private int totalErrors = 0; // errors so far
    
    /**
     * Prints failure report if the result is not true.
     * @param name the name of the test
     * @param result the result to test
     */
    private void assertTrue(String name, boolean result) {
        if (!result) {
            System.out.println();
            System.out.println("***** Test failed ***** "
                    + name + ": " + totalTests);
            totalErrors = totalErrors + 1;
        } /*else{
        System.out.println("----- Test passed ----- "
                   + name + ": " +totalTests);
                   }*/
        totalTests = totalTests + 1;
    }

    /**
     * Prints failure report if the result is not false.
     * @param name the name of the test
     * @param result the result to test
     */
    private void assertFalse(String name, boolean result) {
        assertTrue(name, !result);
    }
}