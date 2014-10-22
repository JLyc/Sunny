package neural_center.initialization;

import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class BasicKnowledge
{
    static final String loadStaticBlock() {
        return "loading";
    }

    private static BasicKnowledge INSTANCE = new BasicKnowledge();
	private static final Map<String, ArrayList<ArrayList<String>>> knowledgeProperties = Maps.newHashMap();

    static {
        System.out.println("initializing");
        knowledgeProperties.put("grammarForListening", new ArrayList<ArrayList<String>>());
        knowledgeProperties.put("recognizedWords.txt", new ArrayList<ArrayList<String>>());
        knowledgeProperties.put("commandsSource", new ArrayList<ArrayList<String>>());
    }

    public static BasicKnowledge getInstance() {
        return INSTANCE;
    }

    public BasicKnowledge(){
        for(String key : knowledgeProperties.keySet()){
            try {
                loadResources(key);
            } catch (IOException e) {
                System.err.println("JLyc \"exception when reading"+key+"\"");
                e.printStackTrace();
                return;
            }
        }
        SunnyInitialization.setStateOkFor(INSTANCE);
    }

	private void loadResources(String resources) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(EnvironmentOfOS.getProperties(resources));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
                out.append(line);
        }
        knowledgeProperties.put(resources, processLines(String.valueOf(out)));
    }
	
	private ArrayList<ArrayList<String>> processLines(String redLine)
	{
		ArrayList<ArrayList<String>> output = new ArrayList<>();

        String[] lineSplit = redLine.split("\\n");
		for (int line = 0; line < lineSplit.length; line++)
		{
			if (lineSplit[line].matches("\\|.+") || lineSplit[line].isEmpty())
			{
				output.add(new ArrayList<String>());
				String[] splitWord = lineSplit[line].split("\\|");
				for (int wordNo = 0; wordNo < splitWord.length; wordNo++)
				{
					output.get(output.size() - 1).add(splitWord[wordNo]);
				}
			}
		}
		return output;
	}
	
	public ArrayList<ArrayList<String>> get(String key)
	{
		return knowledgeProperties.get(key);
	}

	/*try (SeekableByteChannel channel = Files.newByteChannel(wordPowerPath, java.nio.file.StandardOpenOption.READ))
		{
			ByteBuffer buffer = ByteBuffer.allocate(128);

			String loadedDocument = "";

			while (channel.read(buffer) > 0)
			{
				buffer.rewind();
				loadedDocument += Charset.forName(System.getProperty("file.encoding")).decode(buffer).toString();
				buffer.flip();
			}
			return loadedDocument;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}*/

    public static void loadIt(){}
}
