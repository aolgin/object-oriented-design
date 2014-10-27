/*
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

/**
 * Class to represent a price category
 * @author Adam Olgin
 * @version 12-4-13
 */
public class Price {
    /** The name of the price category */
    String cat;
    
    /** The cost per ticket, in dollars */
    int cost;

    /**
     * Constructor for Price
     * @param cat the category
     * @param cost the cost
     */
    Price(String cat, int cost) {
        this.cat = cat;
        this.cost = cost;
    }
    
    /**
     * Convert this price into a string
     * @return the string value of the cost
     *          i.e. "Adult: $10"
     */
    public String toString() {
        return cat + ": $" + cost;
    }
}
