package neural_center.initialization;

import neural_center.listening.ListeningManager;
import neural_center.speaking.SpeakingAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sunny {
	private static ExecutorService defaultExecutor = Executors.newFixedThreadPool(5);

	public static void main(String arg[]) throws NullPointerException {
        defaultExecutor.submit(() -> SpeakingAdapter.getInstance());
        defaultExecutor.submit(() -> ListeningManager.getInstance());
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

    public static ExecutorService getExecutor()
    {
        return defaultExecutor;
    }
}
