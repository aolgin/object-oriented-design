import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io*


public class Complex {
    String name;
    ArrayList<Theater> theaters;
    ArrayList<Show> shows;
    ArrayList<Movie> movies;
    ArrayList<Price> prices;

    Complex(String name, ArrayList<Theater> theaters,
        ArrayList<Show> shows, ArrayList<Movie> movies, ArrayList<Price> prices) {
        this.name = name;
        this.theaters = theaters;
        this.shows = shows;
        this.movies = movies;
        this.prices = prices; //new ArrayList<Price>(Arrays.asList(adult, children, senior))
    }

    static void main(String[] argv) {

    }
    
    /**
     * Prints out the ArrayList of Shows. Checks for duplicate movie
     * entries and combines them into a single line. Separates the movies into different lines
     * Example:
     * Harry Potter: 400 | 500 | 600
     * Great Expectations: 620 | 790
     * @param slist
     */
    String showsToString(ArrayList<Show> slist) {
//        String s1 = slist.get(0).getMovie().name + ": ";
//       
        
        /*
         * To Look into:
         * string.split(String regex) -> String[]
         * string.startsWith(String str) -> boolean
         * string.indexOf(Various forms) -> int
         */
        
//        String q = s.getMovie().getName() + ": " + s.getTime();
        String s = "";
        
        // should append name: time # continuously until the list is done
        for (Show sh : shows) {
            s = s + sh.getMovie().name + ": " + sh.getTime() + " # ";
        }
        
        /*
         * Should now have them split as such ["Harry Potter: 900", "Harry Potter: 1080", ...]
         */
        String[] strings = s.split(" # ");
        String[] strings2;
        
        for (int i = 0; i < strings.length - 1; i++) {
            if (strings[i].substring(0, shows.get(i).getMovie().name.length()).equals(strings[i+1].substring(0, shows.get(i+1).getMovie().name.length()))) {
                strings2[i] = 
            }
        }
        
        
    
    }
    
    // given that the movie is in the system and has a showtime already
    Show findMovieMatch(Movie m) {
        int i = 0;
        boolean hit = false;
        Show s = null;
        while (i < shows.size() && hit == false) {
            if (shows.get(i).mov.equals(m)) {
                hit = true;
                s = shows.get(i);
            }
            else {
                i++;
            }
        }
        return s;
    }
    
    //TODO need to check for showtime collisions
    // it is a given that the movie is one that is being shown, so no worries there
    boolean timeCollision(Movie m, int showtime) {
        Show s = this.findMovieMatch(m);
        
        // if the show times match
        if (s.time == showtime) {
            return true;
        }
        // if no theater was found
        else if (this.findTheater(showtime) == -1) {
            return true;
        }
        else {
            return false;
        }
    }
    
    //TODO method to look for available theater at specified showtime for the given duration (returns an int)
    // return -1 if nothing is found
    int findTheater(int showtime) {
        
    }
    
    //TODO method for actually purchasing a ticket
    
    //TODO need something to read input from a file. Not sure if it will need to be in this class though
    
    void addMovie(String name, int len) {
        Movie newMov = new Movie(name, len);
        if (!movies.contains(newMov)) {
                movies.add(newMov);
        }
        
        //not sure if this is where this should go...
        else {
            throw new RuntimeException("Oops! It looks like that movie is already in the system. Please add a movie that is not already playing at this complex.");
        }
    }
    
    

}
