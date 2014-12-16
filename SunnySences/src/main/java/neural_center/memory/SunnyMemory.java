package neural_center.memory;

import neural_center.initialization.EnvironmentOfOS;
import neural_center.initialization.SunnyInitialization;
import neural_center.memory.initialize_memory.helpers.DataStoresScheme;
import neural_center.memory.initialize_memory.helpers.FileOperators;
import neural_center.memory.initialize_memory.load.LoadGramFile;
import neural_center.memory.initialize_memory.load.LoadLycFile;
import neural_center.memory.initialize_memory.save.SaveGramFile;
import neural_center.memory.initialize_memory.save.SaveLycFile;

import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public class SunnyMemory {
	private static SunnyMemory INSTANCE;

	private static final Path BRAIN = FileSystems.getDefault().getPath(System.getProperty("user.dir"), "Brain", EnvironmentOfOS.getProperties("os"));

	private static Map<String, Future<Object>> loadFileOutput = new HashMap<>();

	static {
		INSTANCE = new SunnyMemory();
		SunnyInitialization.setStateOkFor(INSTANCE);
	}

	public SunnyMemory() {
		// TODO Auto-generated constructor stub
		if(localizeAndCreateMemory()) {

		}

		Path from = FileSystems.getDefault().getPath("nircmd.exe");
		URL inputStream = this.getClass().getClassLoader().getResource("/nircmd/nircmd.exe");
		URL inputStream1 = this.getClass().getClassLoader().getResource("/nircmd.exe");
	}

	public boolean localizeAndCreateMemory() {
		try {
			if(Files.notExists(BRAIN, LinkOption.NOFOLLOW_LINKS)) Files.createDirectories(BRAIN);
			return true;
		} catch(Exception e) {
			e.getStackTrace();
		}
		return false;
	}

	public static Path getBrain() {
		return BRAIN;
	}

	public static void main(String[] args) {
		new SunnyMemory();
		String wanttoknow = "";
		wanttoknow = String.valueOf(BRAIN);

		System.out.println(wanttoknow);
	}

	public void fileControler(String fileKey, FileOperators operation) {
		Path path = FileSystems.getDefault().getPath(EnvironmentOfOS.getProperties(fileKey));
		switch(operation) {
			case LOAD:
//				if(path.toString().endsWith(".lyc"))
//					loadFileOutput.put(fileKey, SunnyInitialization.getExecutor().submit(new LoadLycFile(path.toString())));
//				if(path.toString().endsWith(".gram"))
//					loadFileOutput.put(fileKey, SunnyInitialization.getExecutor().submit(new LoadGramFile(path.toString())));
//				if(path.toString().endsWith(".xml"))
//					loadFileOutput.put(fileKey, SunnyInitialization.getExecutor().submit(new LoadGramFile(path.toString())));
//				break;
			case SAVE:
				if(path.toString().endsWith(".lyc"))
					SunnyInitialization.getExecutor().submit(new SaveLycFile(fileKey));
				if(path.toString().endsWith(".gram"))
					SunnyInitialization.getExecutor().submit(new SaveGramFile(fileKey));
				break;
		}
	}

//	public DataStoresScheme retrieveLoadFileOutput(String key) throws Exception {
//		Future<ArrayList<ArrayList<String>>> output = loadFileOutput.get(key);
//		loadFileOutput.remove(key);
//		return output;
//	}

	public static void enforceInitialization() {
	}
}
