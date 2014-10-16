package neural_center.initialization;

import neural_center.listening.ListeningAdapter;
import neural_center.memory.SunnyMemory;
import neural_center.speaking.SpeakingAdapter;

public class SunnyInitialization extends Thread {
    private static SpeakingAdapter speaking;
    private static BasicKnowledge bknowledge;

    static
    {
        speaking = SpeakingAdapter.getInstance();
    }

	public static void main(String arg[]) throws NullPointerException
	{
        Thread mainSunnyThread = new SunnyInitialization();
        mainSunnyThread.setName("Main Sunny Thread");
        mainSunnyThread.start();
	}

    public static SpeakingAdapter getSpeaking() {
        return speaking;
    }

    public static BasicKnowledge getBknowledge() {
        return bknowledge;
    }

    @Override
    public void run() {
        super.run();

        bknowledge = new BasicKnowledge();

        speaking.say("Initializatin succesfull. Sunny is here");
    }

    public static void turnOffSunny(int state)
    {
        turnOffSunny(state,"Not specified");
    }

    public static void turnOffSunny(int state, String msg)
    {
        switch(state)
        {
            case 1:
            {
                System.err.println(msg);
                System.err.println("Turning off with no saved changes");
                System.exit(1);
            }
            case 2:
            {
                System.err.println(msg);
                System.err.println("Turning off with after saved changes");
                //TODO cleanup method saveWork()
                System.exit(0);
            }
            default:
            {
                System.err.println(msg);
                System.err.println("Unknown state turning off with no saved changes");
                System.exit(1);
            }
        }
    }
}
