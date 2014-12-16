package neural_center.memory.initialize_memory.load;

import neural_center.memory.initialize_memory.helpers.DataStoresScheme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * Created by sochaa on 8. 12. 2014.
 */
public abstract class LoadAnyFile implements Callable<Object>
{
    protected Path path;
    protected ArrayList<ArrayList<String>> proccedFile = new ArrayList<>();

    @Override
    public DataStoresScheme call() throws Exception {
        return new DataStoresScheme(proceedFile());
    }

    public LoadAnyFile(String path)
    {
        this.path = FileSystems.getDefault().getPath(path);
    }

    public LoadAnyFile(URI path) {

        this.path = FileSystems.getDefault().getPath(path.toString());
    }

    protected  BufferedReader loadBufferedReaderFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(loadInputStreamFile()));
        return reader;
    }

    protected InputStream loadInputStreamFile(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path.toString());
        return inputStream;
    }

    protected abstract Object proceedFile() throws IOException;
}
