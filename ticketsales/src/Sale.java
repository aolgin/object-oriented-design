/*
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

/**
 * Class to represent a placement of a sale
 * @author Adam Olgin
 * @version 12-4-13
 */
public class Sale {
    /** The show for the sale */
    Show s;
    
    /** The number of adults ticket purchases in the sale */
    int numAdults;

    /** The number of child ticket purchases in the sale */
    int numChild;
    
    /** The number of senior ticket purchases in the sale */
    int numSen;
    
    /**
     * Constructor for a sale
     * Initializes all ticket numbers to 0
     * @param s the show
     */
    public Sale(Show s) {
        this.s = s;
        this.numAdults = 0;
        this.numChild = 0;
        this.numSen = 0;
    }
    
    /**
     * Is this show sold out?
     * @param adult the requested number of adult tickets
     * @param child the requested number of child tickets
     * @param senior the requested number of senior tickets
     * @return whether or not it is sold out
     */
    public boolean soldOut(int adult, int child, int senior) {
        if (s.theat.capacity <= this.ticketsPerOrder() + s.ticketsSold) {
            return true;
        }
        else {
            numAdults += adult;
            numChild += child;
            numSen += senior;
            s.ticketsSold += (numAdults + numChild + numSen);
            return false;
        }
    }
    
    /**
     * Calculates the total number of tickets per order
     * @return the tickets per order
     */
    int ticketsPerOrder() {
        return numAdults + numChild + numSen;
    }
    
    
}
