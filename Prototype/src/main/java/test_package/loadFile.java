package test_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by socha on 9.10.2014.
 */
public class loadFile {

    public static void main(String[] args) {
        new loadFile();
    }

    public loadFile()
    {
        InputStream inputStream = loadFile.class.getResourceAsStream("/WordPower.txt");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            System.out.println(out.toString());   //Prints the string content read from input stream

        }
        catch (IOException e)
        {}
    }
}
