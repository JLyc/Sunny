package neural_center.memory.initialize_memory.save;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by sochaa on 11. 12. 2014.
 */
public class SaveXmlFile extends SaveAnyFile {


	public SaveXmlFile(String resourcesKey) {
		super(resourcesKey);
	}

	@Override
	protected void saveToBrain() {
		super.saveToBrain();
	}

	@Override
	protected void writeData(BufferedWriter bf) throws IOException {
	}
}
