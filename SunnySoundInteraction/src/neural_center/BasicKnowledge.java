package neural_center;

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
	/*
	 * Key Description of Associated Value java.version Java Runtime Environment
	 * version java.vendor Java Runtime Environment vendor java.vendor.url Java
	 * vendor URL java.home Java installation directory
	 * java.vm.specification.version Java Virtual Machine specification version
	 * java.vm.specification.vendor Java Virtual Machine specification vendor
	 * java.vm.specification.name Java Virtual Machine specification name
	 * java.vm.version Java Virtual Machine implementation version
	 * java.vm.vendor Java Virtual Machine implementation vendor java.vm.name
	 * Java Virtual Machine implementation name java.specification.version Java
	 * Runtime Environment specification version java.specification.vendor Java
	 * Runtime Environment specification vendor java.specification.name Java
	 * Runtime Environment specification name java.class.version Java class
	 * format version number java.class.path Java class path java.library.path
	 * List of paths to search when loading libraries java.io.tmpdir Default
	 * temp file path java.compiler Name of JIT compiler to use java.ext.dirs
	 * Path of extension directory or directories os.name Operating system name
	 * os.arch Operating system architecture os.version Operating system version
	 * file.separator File separator ("/" on UNIX) path.separator Path separator
	 * (":" on UNIX) line.separator Line separator ("\n" on UNIX) user.name
	 * User's account name user.home User's home directory user.dir User's
	 * current working directory
	 */
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
