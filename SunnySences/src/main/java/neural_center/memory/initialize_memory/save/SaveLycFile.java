package neural_center.memory.initialize_memory.save;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sochaa on 9. 12. 2014.
 */
public class SaveLycFile extends SaveAnyFile {

	public SaveLycFile(String resourcesKey) {
		super(resourcesKey);
	}

	@Override
	protected  void writeData(BufferedWriter bf) throws IOException {
		for(ArrayList<String> abc : dataInMemory) {
			StringBuilder stringBuilder = new StringBuilder("|");
			for(String a : abc) {
				stringBuilder.append(a+"|");
			}
			bf.write(stringBuilder.append(System.getProperty("line.separator")).toString());
		}
	}

}
