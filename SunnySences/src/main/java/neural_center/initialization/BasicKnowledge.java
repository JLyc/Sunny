package neural_center.initialization;

import neural_center.memory.initialize_memory.helpers.FileOperators;
import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Deppend on EnvironmentOfOS
 */

public class BasicKnowledge {
    private static BasicKnowledge INSTANCE;
	private static final Map<String, Document> knowledgeProperties = new HashMap();

    private static final String[] loadToMemory = {
            "grammarForListening",
            "recognizedWords",
            "commandsSource",
    };

    private BasicKnowledge() {
        try{
			loadFileToMemory();
			System.out.println("Basic Knowledge load successful: " + loadFileToMemory());
		}catch (Exception e){
			e.printStackTrace();
		}
    }

	private boolean loadFileToMemory() throws Exception
	{
		for (String key : loadToMemory) {
			Sunny.getMemory().bufferFile(key, FileOperators.LOAD);
		}
		return true;
	}

	/**
	 * @param key <table style="width:10%">
	 *                 <tr><td>grammarForListening</td></tr>
	 *                 <tr><td>recognizedWords</td></tr>
	 *                 <tr><td>commandsSource</td></tr>
	 *                 </table>
	 * @return {@link org.w3c.dom.Document} of given directory
	*/
    public Document get(String key)
    {
		if(!knowledgeProperties.containsKey(key))
			getRequiredProperties(key);

		return knowledgeProperties.get(key);
    }

	private void getRequiredProperties(String key){
		try {
			knowledgeProperties.put(key, Sunny.getMemory().retrieveBufferedFile(key).get());
		} catch(Exception e) {
			Sunny.getMemory().bufferFile(key, FileOperators.LOAD); //reload file
			try{
				TimeUnit.SECONDS.sleep(10);
				knowledgeProperties.put(key,Sunny.getMemory().retrieveBufferedFile(key).get());
			}catch (Exception ex){
				Sunny.turnOffSunny(-1, "Unrecoverable error");
			}
		}
	}

    public static BasicKnowledge getInstance(){
		if(INSTANCE == null)INSTANCE = new BasicKnowledge();
		return INSTANCE;
	}
}
