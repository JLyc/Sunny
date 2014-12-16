package neural_center.memory.initialize_memory.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by sochaa on 8. 12. 2014.
 */
public abstract class LoadAnyFile implements Callable<ArrayList<ArrayList<String>>>
{
    protected Path path;
    protected ArrayList<ArrayList<String>> proccedFile = new ArrayList<>();

    @Override
    public ArrayList<ArrayList<String>> call() throws Exception {
        proceedFile();
        return proccedFile;
    }

    public LoadAnyFile(String path)
    {
        this.path = FileSystems.getDefault().getPath(path);
    }

    public LoadAnyFile(URI path) {

        this.path = FileSystems.getDefault().getPath(path.toString());
    }

    protected BufferedReader loadFile() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path.toString());
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader;
    }

    protected abstract void proceedFile() throws IOException;
}
