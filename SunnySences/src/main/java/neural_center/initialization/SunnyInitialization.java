package neural_center.initialization;

import neural_center.listening.ListeningManager;
import neural_center.listening.commandHandler.ReceivedCommand;
import neural_center.memory.Memory;
import neural_center.speaking.SpeakingAdapter;
import ui_fx.FaceThread;
import uniqe_skills.PerformanceTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SunnyInitialization {
    private static SunnyInitialization instance;

    private static ExecutorService defaultExecutor = Executors.newFixedThreadPool(5);
	private SunnyInitialization() throws NullPointerException {
        PerformanceTest.start();
        defaultExecutor.submit(() -> FaceThread.getInstance().buildFace());
        defaultExecutor.submit(EnvironmentOfOS::getInstance);
        defaultExecutor.submit(Memory::getInstance);
        defaultExecutor.submit(BasicKnowledge::getInstance);
        defaultExecutor.submit(SpeakingAdapter::getInstance);
        defaultExecutor.submit(ListeningManager::getInstance);
//        defaultExecutor.submit(() -> ListeningManager.initialize());
        SpeakingAdapter.getInstance().say("hello bitches");
        try {
            System.out.println(ReceivedCommand.updateKnowingWords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ExecutorService getExecutor()
    {
        return defaultExecutor;
    }

    public static void initialize() {
        if (instance == null) instance = new SunnyInitialization();
    }
}
