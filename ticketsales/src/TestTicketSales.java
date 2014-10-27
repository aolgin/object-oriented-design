/*
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

import java.io.IOException;

/**
 * Class for testing the TicketSales system
 * reading from files
 * @author Adam Olgin
 * @version 12-5-13
 */
public class TestTicketSales {
    
    /** Constructor */
    TestTicketSales() {
        // empty constructor
    }
    
    /**
     * Main method for this class
     * @param args the arguments
     * @throws IOException if an error come ups
     */
    public static void main(String[] args) throws IOException {
        
        
        TicketSales tck = new TicketSales();
        tck.initCinema("cinema.txt");
        tck.processOrders("orders.txt");
        String sales = tck.reportSales();
        String manager = tck.managerReport();
        String log = tck.logReport();
        System.out.println(sales);
        System.out.println(manager);
        System.out.println(log);
        
        /*
        String testSalesReport =
                "1,1,960,20,30,40,730\n"
              + "1,1,960,30,20,50,840\n"
              + "1,1,960,40,30,50,0\n"
              + "1,3,1020,20,10,30,510\n"
              + "2,2,990,20,40,20,640\n"
              + "2,2,990,10,5,10,0\n";
           
        String testManagerReport =
                "Report 1\n"
              + "Harry Potter,A,960,300,50,50,90\n"
              + "Harry Potter,A,1080,300,0,0,0\n"
              + "Harry Potter,A,1200,300,0,0,0\n"
              + "Harry Potter,C,1020,500,0,0,0\n"
              + "Harry Potter,C,1140,500,0,0,0\n"
              + "Great Expectations,B,990,90,0,0,0\n"
              + "Great Expectations,B,1210,90,0,0,0\n"
              + "Report 2\n"
              + "Harry Potter,A,960,300,50,50,90\n"
              + "Harry Potter,A,1080,300,0,0,0\n"
              + "Harry Potter,A,1200,300,0,0,0\n"
              + "Harry Potter,C,1020,500,20,10,30\n"
              + "Harry Potter,C,1140,500,0,0,0\n"
              + "Great Expectations,B,990,90,20,40,20\n"
              + "Great Expectations,B,1210,90,0,0,0\n";
        
        //sales.equals(testSalesReport);
        //manager.equals(testManagerReport);
          
        /*
            assertTrue("salesReports", sales.equals(testSalesReport));
            assertTrue("managerReports", manager.equals(testManagerReport));
            
        }
        catch (IOException e) {
            System.out.println("Exception thrown during output tests:");
            System.out.println(e);
            assertTrue("usual", false);
        }*/
    }
    
    
 ///////////////////////////////////////////////////////
    /*
    private int totalTests = 0; // tests run so far
    private int totalErrors = 0; // errors so far
    
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
    /*    totalTests = totalTests + 1;
    }

    
    private void assertFalse(String name, boolean result) {
        assertTrue(name, !result);
    } */
}
