package neural_center.listening;

import neural_center.listening.commandHandler.ReceivedCommand;
import neural_center.listening.listeningAPI.Sphinx4Listener;
import uniqe_skills.PerformanceTest;

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
        System.out.println("Listener \t\t\t load successful: " + true);
        PerformanceTest.result();
    }

    public void onNewCommand(String recordedSound) {
//        commandExecutor.execute(new ProceedCommand(recordedSound));
        //test chatch
        if(!recordedSound.isEmpty()){
        System.out.println(recordedSound);
        new ReceivedCommand(recordedSound);}
    }

    public synchronized static ListeningManager getInstance() {
        if (INSTANCE == null) INSTANCE = new ListeningManager();
        return INSTANCE;
    }
}
