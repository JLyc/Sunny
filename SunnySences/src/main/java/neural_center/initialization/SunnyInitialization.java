package neural_center.initialization;

import neural_center.listening.ListeningManager;
import neural_center.speaking.SpeakingAdapter;

public class SunnyInitialization extends Thread {
    private static SpeakingAdapter speaking;
    private static ListeningManager listening;
    private static BasicKnowledge bknowledge;

    static {
        System.out.println(EnvironmentOfOS.isLoading);
    }

    private SunnyInitialization(String name) {
        super(name);
    }

    public static void main(String arg[]) throws NullPointerException
	{
        SunnyInitialization mainSunnyThread = new SunnyInitialization("Main Sunny Thread");
//        mainSunnyThread.start();
	}

    public static SpeakingAdapter getSpeaking() {
        return speaking;
    }

    public static BasicKnowledge getBknowledge() {
        return bknowledge;
    }

    public static void setStateOkFor(Object object) {
        if(object instanceof ListeningManager)
        {
            listening = (ListeningManager) object;
            System.out.println("JLyc \"listening initialized\"");
        }
        if(object instanceof SpeakingAdapter)
        {
            speaking = (SpeakingAdapter) object;
            System.out.println("JLyc \"speaking initialized\"");
        }
        if(object instanceof BasicKnowledge)
        {
            bknowledge = (BasicKnowledge) object;
            System.out.println("JLyc \"basic knowledge initialized\"");
        }
    }

    @Override
    public void run() {
        super.run();
        while(speaking == null || listening == null || bknowledge == null)
        {
            System.out.println("JLyc \"loading...\"");
        }
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
