package neural_center.memory.initialize_memory.save;

import neural_center.initialization.SunnyInitialization;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sochaa on 9. 12. 2014.
 */
public class SaveGramFile extends SaveAnyFile {

	private BufferedWriter bf;

	public SaveGramFile(String resourcesKey) {
		super(resourcesKey);
	}

	@Override
	protected void writeData(BufferedWriter bf) throws IOException {
		fillHeader(bf);
		for(ArrayList<String> abc : dataInMemory) {
			StringBuilder stringBuilder = new StringBuilder("(");
			for(String a : abc) {
				stringBuilder.append(a+") (");
			}
			stringBuilder.setCharAt(stringBuilder.length()-1,';');
			bf.write(stringBuilder.append(System.getProperty("line.separator")).toString());
		}
	}

	private void fillHeader(BufferedWriter bf) throws IOException {
		bf.write("#JSGF V1.0;\n" +
				"\n" +
				"/**\n" +
				" * Sunny basic knowleadge\n" +
				" */\n" +
				"\n" +
				"grammar Sunny;\n" +
				"\n" +
				"public <commands> = ");
	}

}
