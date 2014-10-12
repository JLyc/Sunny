package neural_center.initialization;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BasicKnowledge
{
		private String recoghnizedEnviroment;
	private String currentWorkingDir;
	private String[] filesToLoad =
		{ "src/neural_center/WordPower.txt", "src/neural_center/CommandsLinuxNoXML.txt", "src/neural_center/listening/sunny_linux.gram" };
	// private ArrayList<ArrayList<String>> wordPower = new ArrayList<>();
	// private ArrayList<ArrayList<String>> commands = new ArrayList<>();
	private Object[] loadedFiles =
		{ new ArrayList<ArrayList<String>>(), new ArrayList<ArrayList<String>>(), new ArrayList<ArrayList<String>>() };
	
	public BasicKnowledge()
	{
		recoghnizedEnviroment = System.getProperty("os.name");
		currentWorkingDir = System.getProperty("user.dir");
		
		for (int index = 0; index < loadedFiles.length; index++)
		{
			loadedFiles[index] = processLines(loadDocument(filesToLoad[index]));
		}
	}
	
	private String loadDocument(String path)
	{
		Path wordPowerPath = Paths.get(path);
		
		try (SeekableByteChannel channel = Files.newByteChannel(wordPowerPath, java.nio.file.StandardOpenOption.READ))
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
		}
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
	
	public ArrayList<ArrayList<String>> getWordPower()
	{
		return ((ArrayList<ArrayList<String>>) loadedFiles[0]);
	}
	
	public ArrayList<ArrayList<String>> getCommands()
	{
		return ((ArrayList<ArrayList<String>>) loadedFiles[1]);
	}
	
	public ArrayList<ArrayList<String>> getGrammer()
	{
		return ((ArrayList<ArrayList<String>>) loadedFiles[2]);
	}
	
	public String getRecoghnizedEnviroment()
	{
		return recoghnizedEnviroment;
	}
	
	public String getCurrentWorkingDir()
	{
		return currentWorkingDir;
	}
	
	@Deprecated
	public static void main(String[] args)
	{
		new BasicKnowledge();
	}
}
