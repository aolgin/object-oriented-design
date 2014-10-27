/*
 * Adam Olgin
 * aolgin@ccs.neu.edu
 * Note: I was unable to get around to setting up the console, but
 * this is able to read from the files and initially the data
 * from them.
 */
import java.io.*;
import java.util.ArrayList;

/**
 * Class for representing a TicketSales System
 * @author Adam Olgin
 * @version 2013-12-4
 */
public class TicketSales {

    /** The list of theaters */
    ArrayList<Theater> theaters;
    
    /** the list of shows */
    ArrayList<Show> shows;
    
    /** the list of movies */
    ArrayList<Movie> movies;
    
    /** the list of prices */
    ArrayList<Price> prices;
    
    /** the list of sales */
    ArrayList<Sale> sales;
    
    /** the current report number */
    int numReports;
    
    /** The Report of the Sales */
    String salesReport;
    
    /** The Error Log */
    String errorLog;

    /** The Constructor for TicketSales */
    TicketSales() {
        this.theaters = new ArrayList<Theater>();
        this.shows = new ArrayList<Show>();
        this.movies = new ArrayList<Movie>();
        this.prices = new ArrayList<Price>();
        this.sales = new ArrayList<Sale>();
        this.numReports = 1;
        this.salesReport = "";
        this.errorLog = "";
    }
    
    /**
     * The main method for this class. Will run the console
     * @param args the arguments
     */
    public static void main(String[] args) {
        /*
         * Potential console information. Commenting out for later use
        // initCinema("cinema.txt");
        String badInput = "I'm sorry, but your input was not recognized.\n"
              + "Please try again and follow the instructions on the console.";
        System.out.println("Welcome to the Movie Theater! "
                + "How may we assist you?\n"
            + "You may type 'menu' at anytime to be returned to this prompt.\n"
            + "Please enter in one of these options: Purchase a Ticket |"
            + "Manager Console\n");
        // add in the escape clause somewhere
        String cmd = System.in();
        if (cmd.equalsIgnoreCase("purchase") || 
                cmd.equalsIgnoreCase("purchase a ticket") ||
            cmd.equalsIgnoreCase("ticket")) {
            System.out.println("Today's Showtimes:\n");
            // print out show times
            System.out.println("Please enter the showtime and movie "
                    + "that you would like to purchase a ticket for.\n"
                + "Use the format [Movie Name, Timeslot] in" 
                + "order to ensure accuracy.\n");
            // check if sold out

        }
        else if (cmd.equalsIgnoreCase("manager") || cmd.equalsIgnoreCase(
                "manager console") ||
            cmd.equalsIgnoreCase("console")) {
            System.out.println(
                    "Welcome to the manager console! "
                    + "Please select one of the following options:\n"
                + "Add Movie | Assign Showtime | Run Report");
            cmd = System.in.read();
            if (cmd.equalsIgnoreCase("add movie")) {
                System.out.println("What is the name of the new movie?\n");
                if (!methodForCheckingIfTheMovieIsAlreadyThere) {
                    System.out.println("How long, in minutes, "
                            + "is the movie?\n");
                    String mov = cmd;
                    cmd = System.in();
                    int t = Integer.parseInt(cmd);
                    this.addMovie(mov, t);
                    System.out.println("Congratulations! " + mov
                            + " has been added to the system's "
                            + "roster of movies.\n"
                        + "Please ensure to assign showtimes to "
                        + "this movie so that tickets may be "
                        + "purchased for it.");
                }
                else {
                    System.out.println("Oops! It looks that movie "
                            + "is already in the system.\n"
                        + "Please try again with a NEW movie.\n");
                }
            }
            else if (cmd.equalsIgnoreCase("assign showtime")) {
                System.out.println("Which movie would you like 
                to assign a showtime to?\n");
                //print movie names with | in between
                cmd = System.in();
                if () {

                }
            }

        }
        else {
            System.out.println(badInput);
        }
        */

    }
    
    /** 
     * Add a new movie to the movies
     * @param name the name of the new movie
     * @param len the length of the new movie
     */
    void addMovie(String name, int len) {
        Movie newMov = new Movie(name, len);
        if (!movies.contains(newMov)) {
            movies.add(newMov);
        }
        
        else {
            throw new RuntimeException("Oops! It looks like "
                    + "that movie is already in the system. "
                    + "Please add a movie that is not already "
                    + "playing at this complex.");
        }
    }
    

    /**
     * Initialize the cinema from a text file
     * @param fileName the name of the file
     * @throws IOException should there be an issue with the file
     */
    void initCinema(String fileName) throws IOException {
        FileData fd = new FileData(fileName);
        String contents = fd.readContents();
        String[] contentArray = contents.split("\n");

        int i = 1;
        while (!contentArray[i].equals("Theaters")) {
            String[] movs = contentArray[i].split(":");
            movies.add(new Movie(movs[0], Integer.parseInt(movs[1])));
            i++;
        }
        i++;
        while (!contentArray[i].equals("Shows")) {
            String[] theats = contentArray[i].split(":");
            theaters.add(new Theater(theats[0], Integer.parseInt(theats[1])));
            i++;
        }
        i++;
        while (!contentArray[i].equals("Prices")) {
            String[] showtimes = contentArray[i].split(",");
            Movie m = this.movies.get(Integer.parseInt(showtimes[0]) - 1);
            Theater t = this.theaters.get(Integer.parseInt(showtimes[1]) - 1);
            shows.add(new Show(m, t, Integer.parseInt(showtimes[2])));
            i++;
        }
        i++;
        while (!contentArray[i].equals("End")) {
            String[] ps = contentArray[i].split(":");
            prices.add(new Price(ps[0], Integer.parseInt(ps[1])));
            i++;
        }
        /*
         * Printing for easy debugging
        System.out.println("Complex Initialized:");
        for (Show s: shows) {
            System.out.println(s.toString());
        }
        for (Price p : prices) {
            System.out.println(p.toString());
        }
        System.out.println("System done initializing");
        */
        
        // initialize the sales
        for (Show s : shows) {
            sales.add(new Sale(s));
        }

    }

    /**
     * Processes the orders from the given file
     * @param fileName the name of the file to read from
     * @throws IOException should there be an issue reading
     */
    void processOrders(String fileName) throws IOException {
        FileData fd = new FileData(fileName);
        String contents = fd.readContents();
        String[] contentArray = contents.split("\n");
        
        String[] lines = contentArray;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].equals("report")) {
                managerReport();
            }
            else {
                String[] oneSale = lines[i].split(",");
                
                Movie m = movies.get(Integer.parseInt(oneSale[0]) - 1);
                Theater t = theaters.get(Integer.parseInt(oneSale[1]) - 1);
                int time = Integer.parseInt(oneSale[2]);
                int adults = Integer.parseInt(oneSale[3]);
                int children = Integer.parseInt(oneSale[4]);
                int seniors = Integer.parseInt(oneSale[5]);
                
                Show temp = new Show(m, t, time);
                for (Sale s : sales) {
                    if (s.s.equals(temp)) {
                        if (s.soldOut(adults, children, seniors)) {
                            salesReport = salesReport + lines[i] + ",0\n";
                            errorLog = errorLog + s.toString() + 
                                    " is sold out!\n";
                        }
                        else {
                            s.s.ticketsSold += adults + children + seniors;
                            int saletotal = adults * 10 + children * 7 
                                    + seniors * 8;
                            salesReport = salesReport + lines[i] + ","
                                    + saletotal + "\n";
                        }
                    }
                }
                
            }
        }
        
        
    }
    
    /**
     * Runs a manager report
     * @return a string with the most current report
     */
    String managerReport() {
        String intro = "Report " + numReports + "\n";
        for (Sale s : sales) {
            intro = intro + s.s.toString() + ", " + s.numAdults
                    + ", " + s.numChild + ", " + s.numSen + "\n";
        }
        System.out.print(intro);
        numReports++;
        return intro;
    }
    
    /**
     * Returns the report of sales
     * @return The sales report in String form
     */
    String reportSales() {
        return salesReport;
    }
    
    /**
     * Returns the error log
     * @return the error log as one string
     */
    String logReport() {
        return errorLog;
    }
}
