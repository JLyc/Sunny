package neural_center.listening;

import neural_center.initialization.SunnyInitialization;
import neural_center.listening.commandHandler.ProceedCommand;
import neural_center.listening.listeningAPI.ListenerInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jlyc on 12.10.2014.
 */
public final class ListeningManager {
    private static final ListeningManager INSTANCE = new ListeningManager();
    private static final Map<Float, ListenerInterface> LISTENING_SOURCES = new HashMap<>();
    private static Thread currentListeningClass;

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
                LISTENING_SOURCES.get(getHighestVersion()).deinitializeListener();
                LISTENING_SOURCES.remove(getHighestVersion());
            }
        }
        SunnyInitialization.setStateOkFor(INSTANCE);
    }

    private static void useLatestListener() throws Exception{
        LISTENING_SOURCES.get(getHighestVersion()).initializeListener(INSTANCE);
        currentListeningClass = new Thread(LISTENING_SOURCES.get(getHighestVersion()));
        currentListeningClass.start();
        currentListeningClass.wait();
    }

    public void onNewCommand(String recordedSound)
    {
        new ProceedCommand(recordedSound);
    }

    private static Float getHighestVersion() {
        float higherKey = 0f;
        for(float key : LISTENING_SOURCES.keySet())
        {
            if(key>higherKey){higherKey = key;}
        }
        return higherKey;
    }

    public boolean stopListening(){
        try {
            currentListeningClass.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean startListening(){
        currentListeningClass.notify();
        return false;
    }
}
