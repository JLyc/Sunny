package neural_center.initialization;

import neural_center.listening.ListeningAdapter;
import neural_center.listening.SunnyListen;
import neural_center.memory.SunnyMemory;
import neural_center.speaking.SpeakingAdapter;
import neural_center.speaking.SpeakingInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SunnyInitialization extends Thread {
    private static ListeningAdapter listening;
    private static SpeakingAdapter speaking;
    private static SunnyMemory memory;
    private static BasicKnowledge bknowledge;
	
	public static void main(String arg[]) throws NullPointerException
	{
        Thread mainSunnyThread = new SunnyInitialization();
        mainSunnyThread.setName("Main Sunny Thread");
        mainSunnyThread.start();
        voice = SpeakingAdapter.getInstance();
		listen = new SunnyListen();
		memory = new SunnyMemory();
		bknowledge = new BasicKnowledge();
		listen.start();
	}

    public static void loadAdapters() {


        List<SpeakingAdapter> a = new ArrayList<>();
        List<SunnySencesAdapter<SpeakingInterface>> b = new ArrayList<>();
        List<SunnySencesAdapter<?>> c = new ArrayList<>();

        Map<String, SunnySencesAdapter<?>> d = new HashMap<>();
        speaking = new SpeakingAdapter();
        listening = new ListeningAdapter();

        a.add(speaking);
        b.add(speaking);
        c.add(speaking);
        d.put("1", speaking);


        a.get(1).say("");
        a.get(1).say("");
        a.get(1).say("");
        d.entrySet().

    }

    public static Object getAdapters(String adapter) {
        return adapters.get(adapter);
    }

    public static BasicKnowledge getBknowledge() {
        return bknowledge;
    }

    private static boolean testSunnyFunction() {
        boolean okCheck = true;

        if (listen == null) {
            System.err.println("no listen initialized");
            okCheck = false;
        }
        if (voice == null) {
            System.err.println("no voice initialized");
            okCheck = false;
        }
        if (memory == null) {
            System.err.println("no memory initialized");
            okCheck = false;
        }
        if (bknowledge == null) {
            System.err.println("no basic knowledge initialized");
            okCheck = false;
        }

        return okCheck;
    }

    @Override
    public void run() {
        super.run();

        bknowledge = new BasicKnowledge();

        if (testSunnyFunction()) {
            speaking.say("Initializatin succesfull. Sunny is here");
        } else {
            System.err.println("Ending application to prevent not funcional run");
            System.exit(1);
        }
    }
}
