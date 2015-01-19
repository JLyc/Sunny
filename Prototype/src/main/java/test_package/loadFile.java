package test_package;

import java.io.*;

/**
 * Created by socha on 9.10.2014.
 */
public class loadFile {

    public static void main(String[] args) {
        try {
            new loadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public loadFile() throws FileNotFoundException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("test_package/WordPower.txt");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            System.out.println(out.toString());   //Prints the string content read from input stream

        } catch (IOException e) {
        }
    }
}
