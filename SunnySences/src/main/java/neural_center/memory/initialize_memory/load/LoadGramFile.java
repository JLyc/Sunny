package neural_center.memory.initialize_memory.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by sochaa on 8. 12. 2014.
 */
public class LoadGramFile extends LoadAnyFile {

    public LoadGramFile(String path) {
        super(path);
    }

    public LoadGramFile(URI path) {
        super(path);
    }

    @Override
    protected Object proceedFile() throws IOException {
        BufferedReader reader = loadBufferedReaderFile();
        String line;
        for (int index=0;(line = reader.readLine()) != null;) {
            if (line.matches("public <.+> =.+")) {
                proccedFile.add(new ArrayList<String>());
                String clearString = (line.substring(line.indexOf("(")+1, line.lastIndexOf(")")).toLowerCase());
                for(String parts: clearString.split("\\)\\s\\(")) {
                    proccedFile.get(index).add(parts);
                }
                index++;
            }
        }
        return proccedFile;
    }
}
