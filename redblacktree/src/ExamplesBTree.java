import java.util.ArrayList;
import tester.Tester;
import java.util.Comparator;
import java.util.Iterator;

/*
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

/**
 * Class for testing BTree implementation
 * @author Adam
 * @version 10-30-13
 */
public class ExamplesBTree {
    
    //ExamplesBTree() {
        // empty constructor
    //}
    
    private static Comparator<String> lexComp = new StringByLex();
    private static Comparator<String> lexComp2 = new StringByLex();
    private static Comparator<String> lengthComp = new StringByLength();

    private BTree btLex = BTree.binTree(lexComp); // will require fixing
    private BTree btLexLeaf = BTree.binTree(lexComp); // will require fixing
    private BTree btLength = BTree.binTree(lengthComp);

    private ArrayList<String> btLexList = new ArrayList<String>();
    private ArrayList<String> btLengthList = new ArrayList<String>();

    
    void initTrees() {
        btLex.insert("strawberry");
        btLex.insert("kiwi");
        btLex.insert("banana");
        btLex.insert("apple");
        btLex.insert("raspberry");
        btLex.insert("apple");        // checking if duplicates are added
        
        btLexLeaf.insert("apple");
        btLexLeaf.insert("mango");
        
        btLength.insert("strawberry");
        btLength.insert("kiwi");
        btLength.insert("banana");
        btLength.insert("apple");
        btLength.insert("raspberry");
        btLength.insert("apple");     // checking if duplicates are added
        
        btLexList.add("apple");
        btLexList.add("banana");
        btLexList.add("kiwi");
        btLexList.add("raspberry");
        btLexList.add("strawberry");
        
        btLengthList.add("kiwi");
        btLengthList.add("apple");
        btLengthList.add("banana");
        btLengthList.add("raspberry");
        btLengthList.add("strawberry");
    }
    
    /**
    * For testing the accessor methods
    * @param t the Tester
    */
    void testAccessors(Tester t) {
        initTrees();
        t.checkExpect(btLex.contains("orange"), false);
        t.checkExpect(btLex.contains("apple"), true);
        t.checkExpect(btLex.hashCode() == btLength.hashCode(), false);
        t.checkExpect(btLex.hasChild("left"), true);
        t.checkExpect(btLex.hasChild("right"), true);
        t.checkExpect(btLexLeaf.hasChild("left"), false);
        t.checkExpect(btLexLeaf.hasChild("right"), true);
        //t.checkExpect(btLex.getChild("left"), true);
        //t.checkExpect(btLex.getChild("right"), true);
    }
    
    /**
    * For testing the usual methods
    * @param t the Tester
    */ 
    void testUsual(Tester t) {
        initTrees();
        t.checkExpect(btLex.toString(), 
                "apple, banana, kiwi, raspberry, strawberry");
        t.checkExpect(btLex.equals(btLength), false);
        t.checkExpect(btLex.isLeaf(), false);
        t.checkExpect(btLex.isNode(), true);
        t.checkExpect(btLexLeaf.isLeaf(), true);
        t.checkExpect(btLexLeaf.isNode(), false);
        t.checkExpect(btLex.repOK(), true);
        t.checkExpect(btLexLeaf.repOK(), true);
    }
    
    /**
     * For testing the comparator methods
     * @param t the Tester
     */
    void testComps(Tester t) {
        t.checkExpect(lexComp.toString(), "StringByLex");
        t.checkExpect(lengthComp.toString(), "StringByLength");
        t.checkExpect(lexComp.equals(lengthComp), false);
        t.checkExpect(lengthComp.equals(lexComp), false);
        t.checkExpect(lexComp.equals(lexComp2), true);
        t.checkExpect(lexComp.compare("a", "b"), -1); // perhaps unnecessary
        t.checkExpect(lengthComp.compare("b", "a"), 0); // perhaps unnecessary
    }
    
}
