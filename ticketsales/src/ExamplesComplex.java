import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;


public class ExamplesComplex {
    
    ExamplesComplex() {}
    
    Movie hp = new Movie("Harry Potter", 102);
    Movie ge = new Movie("Great Expectations", 115);
    
    Theater a = new Theater("A", 300);
    Theater b = new Theater("B", 90);
    Theater c = new Theater("C", 500);
    
    Show hp1 = new Show(hp, 1, 960);
    Show hp2 = new Show(hp, 1, 1080);
    Show hp3 = new Show(hp, 1, 1200);
    Show hp4 = new Show(hp, 3, 1020);
    Show hp5 = new Show(hp, 3, 1140);
    Show ge1 = new Show(ge, 2, 990);
    Show ge2 = new Show(ge, 2, 1210);
    
    Price adult = new Price("Adult", 10);
    Price child = new Price("Children", 7);
    Price senior = new Price("Senior", 8);
    
    ArrayList<Movie> movs;// = new ArrayList<Movie>(Arrays.asList(hp, ge));
    ArrayList<Theater> theats;// = new ArrayList<Theater>(Arrays.asList(a, b, c));
    ArrayList<Show> shaws;// = new ArrayList<Show>(Arrays.asList(hp1, hp2, hp3, hp4, hp5, ge1, ge2));
    ArrayList<Price> pisces;// = new ArrayList<Price>(Arrays.asList(adult, child, senior));
    
    Sale s1 = new Sale(hp1, 2, 0, 0);
    Sale s2 = new Sale(hp1, 1, 2, 0);
    Sale s3 = new Sale(hp2, 2, 1, 2);

    ArrayList<Sale> saline = new ArrayList<Sale>(Arrays.asList(s1, s2, s3));
    
    
    Complex myComplex = new Complex("My Cinema", theats, shaws, movs, pisces);
    
    String file1 = "cinema.txt";
    String file2 = "orders.txt";

    FileData fd = new FileData(file1);
    FileData fd2 = new FileData(file2);

    TicketSales cin = new TicketSales("MyCin", theats, shaws, movs, pisces, saline);
    
    void testShowMethods(Tester t) throws IOException {
        cin.initCinema(file1);
    }
    
    /*
    void testComplexMethod(Tester t) {
        t.checkExpect(myComplex.findMovieMatch(hp), hp);
        t.checkExpect(myComplex.findMovieMatch(new Movie("LotR", 180)), new Movie("LotR", 180)); // should return an error
        
        System.out.println(myComplex.showsToString(shaws));
    } */
}
