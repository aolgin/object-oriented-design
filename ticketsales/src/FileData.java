/*
 * Adam Olgin
 * aolgin@ccs.neu.edu
 */

import java.io.*;
import java.util.ArrayList;

/**
 * Class for creating a string out of the contents
 * of a file
 * @author Adam Olgin
 * @version 12-4-13
 */
public class FileData {
    /** The name of the file to read from */
    String filename;
    
    /**
     * The constructor for this class
     * @param filename the name of the file
     */
    FileData(String filename) {
        this.filename = filename;
    }
    
    /**
     * Read the contents of the given file
     * and covert them into a string
     * @return a string version of the file contents
     * @throws IOException should there be an issue reading the file
     */
    public String readContents() throws IOException {
        String s = "";
        try {
            ReadFile file = new ReadFile(filename);
            ArrayList<String> lines = file.openFile();

            for (int i = 0; i < lines.size(); i++) {
                s += lines.get(i) + "\n";
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return s;
    }   
}

/**
 * Class for a file reading algorithm
 * @author Adam Olgin
 * @version 12-4-13
 */
class ReadFile {
    /** The file path of the string */
    private static String path;
    
    /**
     * Constructor for this class
     * @param filePath the path of the file
     */
    public ReadFile(String filePath) {
        path = filePath;
    }

    /** Read from the file and convert the read data into an arrayList of
     * String (one element per line of text)
     * @return An ArrayList<String> containing the data from the file
     * @throws IOException should there be an issue reading the file
     */
    public ArrayList<String> openFile() throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);

        int numLines = this.getNumLines();
        ArrayList<String> textData = new ArrayList<String>();

        for (int i = 0; i < numLines; i++) {
            textData.add(textReader.readLine());
        }

        textReader.close();
        return textData;
    }

    /**
     * Calculate the number of lines within the text file
     * @return the number of lines in the text file
     * @throws IOException should there be an issue reading from the file
     */
    private int getNumLines() throws IOException {
        FileReader fileToRead = new FileReader(path);
        BufferedReader bf = new BufferedReader(fileToRead);
        @SuppressWarnings("unused")
        String line;
        int numLines = 0;
        while ((line = bf.readLine()) != null) {
            numLines++;
        }
        bf.close();
        return numLines;
    }
}