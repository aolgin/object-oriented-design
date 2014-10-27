/*
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

/**
 * Class to represent one showtime
 * @author Adam Olgin
 * @version 12-4-13
 */
public class Show {
    /** The movie showing */
    Movie mov;
    
    /** The theater showing in */
    Theater theat;
    
    /** The time it will be showing, in minutes after 0:00 */
    int time;
    
    /** The number of tickets sold */
    int ticketsSold;
    
    /** Is this show sold out or not? */
    boolean soldOut;

    /**
     * Constructor for show
     * @param mov the movie
     * @param theat the theater
     * @param time the time
     */
    Show(Movie mov, Theater theat, int time) {
        this.mov = mov;
        this.theat = theat;
        this.time = time;
        this.ticketsSold = 0;
        this.soldOut = false;
    }

    /**
     * Purchase one ticket for this show
     * May end up scrapping this method
     */
    void purchaseTicket() {
        if (!soldOut) {
            this.ticketsSold++;
        }
    }
    
    /**
     * Convert this show into a string
     * @return the string version of this show
     *          i.e. "Harry Potter, B, 1920"
     */
    public String toString() {
        return mov.name + ", " + theat.name + ", " + time;
    }



}
