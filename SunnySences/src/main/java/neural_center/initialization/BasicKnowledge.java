package neural_center.initialization;

import neural_center.memory.SunnyMemory;
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

	static {
		EnvironmentOfOS.enforceInitialization();
		SunnyMemory.enforceInitialization();
	}
	private static final Map<String, Document> knowledgeProperties = new HashMap();

    private static final String[] loadToMemory = {
            "grammarForListening",
            "recognizedWords",
            "commandsSource",
    };

    private BasicKnowledge() {
		loadBasicKnowledgeToMemory();
		System.out.println("Environment of OS load successful: " + loadBasicKnowledgeToMemory());
    }

	private boolean loadBasicKnowledgeToMemory(){
		for (String key : loadToMemory) {
			SunnyMemory.bufferFile(key, FileOperators.LOAD);
		}
		return true;
	}

    public Document get(String key)
    {
		if(!knowledgeProperties.containsKey(key))
			getRequiredProperties(key);

		return knowledgeProperties.get(key);
    }

	private void getRequiredProperties(String key){
		try {
			knowledgeProperties.put(key, SunnyMemory.retrieveBufferedFile(key).get());
		} catch(Exception e) {
			SunnyMemory.bufferFile(key, FileOperators.LOAD); //reload file
			try{
				TimeUnit.SECONDS.sleep(10);
				knowledgeProperties.put(key, SunnyMemory.retrieveBufferedFile(key).get());
			}catch (Exception ex){
				SunnyInitialization.turnOffSunny(-1, "Unrecoverable error");
			}
		}
	}

    public static BasicKnowledge enforceInitialization(){
		if(INSTANCE == null)
			INSTANCE = new BasicKnowledge();

		return INSTANCE;
	}
}
