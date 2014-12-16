package neural_center.memory.initialize_memory.save;

import neural_center.initialization.EnvironmentOfOS;
import neural_center.initialization.SunnyInitialization;
import neural_center.memory.SunnyMemory;
import neural_center.memory.initialize_memory.FileOperators;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * Created by sochaa on 9. 12. 2014.
 */
public abstract class SaveAnyFile implements Callable<Boolean> {
	protected String resourcesKey;

	protected Path pathInBrain;
	protected ArrayList<ArrayList<String>> dataInMemory;
	protected ArrayList<ArrayList<String>> dataToMerge;
	protected ArrayList<ArrayList<String>> dataToSave;

	public SaveAnyFile(String resourcesKey) {
		this.resourcesKey = resourcesKey;
		pathInBrain = FileSystems.getDefault().getPath(String.valueOf(SunnyMemory.getBrain()), EnvironmentOfOS.getProperties(resourcesKey));
		if(Files.notExists(pathInBrain, LinkOption.NOFOLLOW_LINKS)) {
			try {
				Files.createFile(pathInBrain);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void saveToBrain() {
		try(BufferedWriter writer = Files.newBufferedWriter(pathInBrain, Charset.forName("UTF-8"), WRITE, TRUNCATE_EXISTING)) {
			preaperDataToBeSaved();
			writeData(writer);
		} catch(IOException e) {
			System.err.println(e);
		}
	}

	private void preaperDataToBeSaved() throws IOException {
		loadMemoryData();
		loadFileData();
		if(dataInMemory == null || dataToMerge == null) throw new IOException("\"JLyc\" no data presented");


	}

	public static void mergeList(ArrayList<ArrayList<String>> imputListA, ArrayList<ArrayList<String>> imputListB) {

		for(ArrayList<String> listA : imputListA) {
			if(!imputListB.contains(listA))
				imputListB.add(listA);
		}
	}

	protected abstract void writeData(BufferedWriter bf) throws IOException;

	protected void loadMemoryData() {
		dataInMemory = SunnyInitialization.getBknowledge().get(resourcesKey);
	}

	protected boolean loadFileData() {
		try {
			SunnyMemory memory = SunnyInitialization.getMemory();
			memory.fileControler(resourcesKey, FileOperators.LOAD);
			while(!memory.retrieveLoadFileOutput(resourcesKey).isDone()) {
				TimeUnit.SECONDS.sleep(1);
			}
			dataToMerge = memory.retrieveLoadFileOutput(resourcesKey).get();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean call() throws Exception {
		saveToBrain();
		if(SunnyInitialization.LycLog) {System.out.println(dataInMemory.toString() + dataToSave + " SaveAnyFile");}
		return new Boolean(true);
	}
}
