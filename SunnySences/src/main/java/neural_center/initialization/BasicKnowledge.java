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

    private static String[] loadToMemory = {
            "grammarForListening",
            "recognizedWords",
            "commandsSource",
    };

    static {
        INSTANCE = new BasicKnowledge();
        SunnyInitialization.setStateOkFor(INSTANCE);
    }

    private BasicKnowledge() {
        for (String key : loadToMemory) {
			SunnyInitialization.getMemory().fileControler(key, FileOperators.LOAD);
        }
    }

    public Document get(String key)
    {
		if(!knowledgeProperties.containsKey(key))
		{
			try {
				knowledgeProperties.put(key, SunnyInitialization.getMemory().retrieveLoadFileOutput(key).get());
			} catch(Exception e) {
				SunnyInitialization.getMemory().fileControler(key, FileOperators.LOAD);
				try{
					TimeUnit.SECONDS.sleep(10);
					knowledgeProperties.put(key, SunnyInitialization.getMemory().retrieveLoadFileOutput(key).get());
				}catch (Exception ex)
				{
					SunnyInitialization.turnOffSunny(-1, "Unrecoverable error");
				}
			}
		}
		Document output = knowledgeProperties.get(key);
		if(output==null)
		{
			if(SunnyInitialization.LycLog) {System.out.println("error here" + " BasicKnowledge");}
		}
		return output;
    }

    public static BasicKnowledge enforceInitialization(){
		if(INSTANCE == null)
			INSTANCE = new BasicKnowledge();

		return INSTANCE;
	}
}
