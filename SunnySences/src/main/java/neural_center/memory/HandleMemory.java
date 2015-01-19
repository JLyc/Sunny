package neural_center.memory;

import neural_center.initialization.EnvironmentOfOS;
import neural_center.initialization.SunnyInitialization;
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
 * Created by JLyc on 1/18/2015.
 */
public class HandleMemory {
	private static Map<String, Future<Document>> loadFileOutput = new HashMap<>();

	public void fileController(String fileKey, FileOperators operation) {
		Path path = FileSystems.getDefault().getPath(EnvironmentOfOS.getProperties(fileKey));
		switch (operation) {
			case LOAD:
				if(path.toString().endsWith(".xml"))
					loadFileOutput.put(fileKey, SunnyInitialization.getExecutor().submit(new LoadXmlFile(path.toString())));
				break;
			case SAVE:
				if (path.toString().endsWith(".xml"))
					SunnyInitialization.getExecutor().submit(new SaveXmlFile(fileKey));
				break;
		}
	}

	public Future<Document> retrieveLoadFileOutput(String key) {
		Future<Document> output = loadFileOutput.get(key);
		loadFileOutput.remove(key);
		return output;
	}
}
