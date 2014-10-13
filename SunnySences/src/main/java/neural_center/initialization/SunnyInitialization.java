package neural_center.initialization;

import neural_center.listening.ListeningAdapter;
import neural_center.memory.SunnyMemory;
import neural_center.speaking.SpeakingAdapter;

public class SunnyInitialization extends Thread {
    private static ListeningAdapter listening;
    private static SpeakingAdapter speaking;
    private static SunnyMemory memory;
    private static BasicKnowledge bknowledge;

    static
    {
        speaking = SpeakingAdapter.getInstance();
        listening = ListeningAdapter.getInstance();
    }

	public static void main(String arg[]) throws NullPointerException
	{
        Thread mainSunnyThread = new SunnyInitialization();
        mainSunnyThread.setName("Main Sunny Thread");
        mainSunnyThread.start();
	}

    public static ListeningAdapter getListening() {
        return listening;
    }

    public static void setListening(ListeningAdapter listening) {
        SunnyInitialization.listening = listening;
    }

    public static SpeakingAdapter getSpeaking() {
        return speaking;
    }

    public static void setSpeaking(SpeakingAdapter speaking) {
        SunnyInitialization.speaking = speaking;
    }

    public static BasicKnowledge getBknowledge() {
        return bknowledge;
    }

    private static boolean testSunnyFunction() {
        boolean okCheck = true;

        if (listening == null) {
            System.err.println("no listen initialized");
            okCheck = false;
        }
        if (speaking == null) {
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
