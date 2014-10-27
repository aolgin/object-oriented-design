/**
 * Class to represent a movie
 * @author Adam Olgin
 * @version 12-4-13
 */
public class Movie {
    /** The name of the movie */
    String name;
    
    /** The length of the movie */
    int length;

    /**
     * The constructor for a movie
     * @param name the name of the movie
     * @param length its length
     */
    Movie(String name, int length) {
        this.name = name;
        this.length = length;
    }
    
    /**
     * Is this movie equal to the given object?
     * @param o the object of questionable equality
     * @return if they are equal
     */
    public boolean equals(Object o) {
        if (o instanceof Movie) {
            Movie m = (Movie) o;
            return this.name.equals(m.name) &&
                    this.length == m.length;
        }
        else {
            return false;
        }
    }
    
}
