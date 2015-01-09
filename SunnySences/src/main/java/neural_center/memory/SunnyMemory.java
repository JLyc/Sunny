package neural_center.memory;

import neural_center.initialization.EnvironmentOfOS;
import neural_center.initialization.SunnyInitialization;
import neural_center.memory.initialize_memory.helpers.FileOperators;
import neural_center.memory.initialize_memory.load.LoadXmlFile;
import neural_center.memory.initialize_memory.save.SaveXmlFile;
import org.w3c.dom.Document;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public class SunnyMemory {
    private static SunnyMemory INSTANCE;

    public static final Path BRAIN = FileSystems.getDefault().getPath(System.getProperty("user.dir"), "Brain", EnvironmentOfOS.getProperties("os"));

    private static Map<String, Future<Document>> loadFileOutput = new HashMap<>();

    static {
        INSTANCE = new SunnyMemory();
        SunnyInitialization.setStateOkFor(INSTANCE);
    }

    public SunnyMemory() {
        // TODO Auto-generated constructor stub
        if (localizeAndCreateMemory()) {
            return;
        }

//        Path from = FileSystems.getDefault().getPath("nircmd.exe");
//        URL inputStream = this.getClass().getClassLoader().getResource("/nircmd/nircmd.exe");
//        URL inputStream1 = this.getClass().getClassLoader().getResource("/nircmd.exe");
    }

    public boolean localizeAndCreateMemory() {
        try {
            if (Files.notExists(BRAIN, LinkOption.NOFOLLOW_LINKS)) Files.createDirectories(BRAIN);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return false;
    }

    public void fileControler(String fileKey, FileOperators operation) {
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

	public Future<Document> retrieveLoadFileOutput(String key) throws Exception {
		Future<Document> output = loadFileOutput.get(key);
		loadFileOutput.remove(key);
		return output;
	}

    public static void enforceInitialization() {
    }
}
