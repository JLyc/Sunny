package neural_center.initialization;

import neural_center.consciousness.Consciousness;
import neural_center.listening.ListeningManager;
import neural_center.memory.Memory;
import neural_center.speaking.SpeakingAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sunny {
    private static SpeakingAdapter speaking;
    private static ListeningManager listening;
    private static BasicKnowledge bknowledge;
    private static EnvironmentOfOS environmentOfOS;
    private static Consciousness consciousness;
	private static Memory memory;

	private static ExecutorService defaultExecutor = Executors.newFixedThreadPool(5);

	public static void main(String arg[]) throws NullPointerException {
//        defaultExecutor.execute(() -> SunnyFace.show());
//        SunnyFace.show();
//        defaultExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                SunnyFace.show();
//            }
//        });
        environmentOfOS = EnvironmentOfOS.getInstance();
        memory = Memory.getInstance();
        bknowledge = BasicKnowledge.getInstance();
        defaultExecutor.execute(() -> SpeakingAdapter.initSpeaking());
//        defaultExecutor.execute(() -> ListeningManager.initListening());
//        Consciousness.ofSunny();
    }



    public static void setStateOkFor(Object object) {
        if(object instanceof ListeningManager)
        {
            listening = (ListeningManager) object;
            System.out.println("JLyc \"listening initialized\"");
            return;
        }
        if(object instanceof SpeakingAdapter)
        {
            speaking = (SpeakingAdapter) object;
            System.out.println("JLyc \"speaking initialized\"");
            return;
        }
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

    public static SpeakingAdapter getSpeaking() {
        return speaking;
    }

    public static ListeningManager getListening() {
        return listening;
    }

    public static BasicKnowledge getBknowledge() {
        return bknowledge;
    }

    public static EnvironmentOfOS getEnvironmentOfOS() {
        return environmentOfOS;
    }

    public static Memory getMemory() {
        return memory;
    }

    public static ExecutorService getExecutor()
    {
        return defaultExecutor;
    }
}
