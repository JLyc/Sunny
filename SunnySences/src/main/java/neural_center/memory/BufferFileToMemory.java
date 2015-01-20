package neural_center.memory;

import neural_center.initialization.Sunny;
import neural_center.memory.initialize_memory.helpers.FileOperators;
import neural_center.memory.initialize_memory.load.LoadXmlFile;
import neural_center.memory.initialize_memory.save.SaveXmlFile;
import org.w3c.dom.Document;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by sochaa on 13. 1. 2015.
 */
public abstract class BufferFileToMemory {

    private static final Map<String, Future<Document>> loadFileOutput = new HashMap<>();

    public void bufferFile(String fileKey, FileOperators operation) {
        Path path = FileSystems.getDefault().getPath(Sunny.getEnvironmentOfOS().getProperties(fileKey));
        if (path.toString().endsWith(".xml")){
            switch (operation) {
                case LOAD:
                    loadFileOutput.put(fileKey, Sunny.getExecutor().submit(new LoadXmlFile(path.toString())));
                    break;
                case SAVE:
                    Sunny.getExecutor().submit(new SaveXmlFile(fileKey));
                    break;
            }
        }
    }

    public Future<Document> retrieveBufferedFile(String key) {
        Future<Document> output = loadFileOutput.get(key);
        loadFileOutput.remove(key);
        return output;
    }
}
