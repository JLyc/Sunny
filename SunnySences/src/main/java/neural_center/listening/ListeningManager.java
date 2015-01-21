package neural_center.listening;

import neural_center.listening.listeningAPI.Sphinx4Listener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* Created by jlyc on 12.10.2014.
*/
public final class ListeningManager {
    private static ListeningManager INSTANCE;

    private static ExecutorService commandExecutor = Executors.newFixedThreadPool(10);

    private ListeningManager() {
        commandExecutor.execute(new Sphinx4Listener());
    }

    public void onNewCommand(String recordedSound) {
//        commandExecutor.execute(new ProceedCommand(recordedSound));
        //test chatch
        System.out.println(recordedSound);
    }

    public static ListeningManager getInstance() {
        if (INSTANCE == null) INSTANCE = new ListeningManager();
        return INSTANCE;
    }
}
