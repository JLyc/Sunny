package neural_center.memory.initialize_memory.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by JLyc on 7. 12. 2014.
 */
public class LoadLycFile extends LoadAnyFile {

    public LoadLycFile(String path)
    {
        super(path);
    }

    public LoadLycFile(URI path) {
        super(path);
    }

    protected Object proceedFile() throws IOException {
        BufferedReader reader = loadBufferedReaderFile();
        String line;
        for (int index=0;(line = reader.readLine()) != null;) {
            if(!(line.startsWith("//") || line.isEmpty())&& line.startsWith("|")) {
                proccedFile.add(new ArrayList<String>());
                for (String split : line.toLowerCase().split("\\|")) {
                    proccedFile.get(index).add(split);
                }
                proccedFile.get(index).remove(0);
                index++;
            }
        }
        return proccedFile;
    }
}
