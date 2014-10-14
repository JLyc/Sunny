package neural_center.listening;

import neural_center.listening.commandHandler.ProceedCommand;
import neural_center.listening.listeningAPI.ListenerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jlyc on 12.10.2014.
 */
public class ListeningManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListeningManager.class);
    private static final ListeningManager INSTANCE = new ListeningManager();
    private static final Map<Float, ListenerInterface> LISTENING_SOURCES = new HashMap<>();

    private ListeningManager() {}

    public static void requestRegistration(ListenerInterface sourceToRegister)
    {
        if(!LISTENING_SOURCES.containsKey(sourceToRegister.getVersion())) {
            LISTENING_SOURCES.put(sourceToRegister.getVersion(), sourceToRegister);
        }
        while(true){
            try {
               useLatestListener();
               break;
            }  catch (Exception e) {
                LISTENING_SOURCES.get(getHigherVersion()).deinitializeListener();
                LISTENING_SOURCES.remove(getHigherVersion());
            }
        }
    }

    private static void useLatestListener() throws Exception{
        LISTENING_SOURCES.get(getHigherVersion()).initializeListener(INSTANCE);
    }

    public void onNewCommand(String recordedSound)
    {
        new ProceedCommand(recordedSound);
    }

    private static Float getHigherVersion() {
        float higherKey = 0f;
        for(float key : LISTENING_SOURCES.keySet())
        {
            if(key>higherKey){higherKey = key;}
        }
        return higherKey;
    }
}
