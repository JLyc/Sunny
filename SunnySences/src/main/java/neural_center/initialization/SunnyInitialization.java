package neural_center.initialization;

import neural_center.consciousness.Consciousness;
import neural_center.listening.ListeningManager;
import neural_center.memory.SunnyMemory;
import neural_center.speaking.SpeakingAdapter;
import ui_fx.SunnyFace;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SunnyInitialization {
	public static final boolean LycLog = true;
    private static SpeakingAdapter speaking;
    private static ListeningManager listening;
    private static BasicKnowledge bknowledge;
    private static EnvironmentOfOS environmentOfOS;
    private static Consciousness consciousness;
	private static SunnyMemory memory;

	private static ExecutorService defaultExecutor = Executors.newFixedThreadPool(5);
	private static ExecutorService persistenExecutor = Executors.newFixedThreadPool(5);

	public static void main(String arg[]) throws NullPointerException
	{
        defaultExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SunnyFace.show();
            }
        });
		EnvironmentOfOS.enforceInitialization();
		SunnyMemory.enforceInitialization();
		defaultExecutor.execute(new Runnable() {
			@Override
			public void run() {
				BasicKnowledge.enforceInitialization();
			}
		});
        defaultExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SpeakingAdapter.enforceInitialization();
            }
        });
        defaultExecutor.execute(new Runnable() {
			@Override
			public void run() {
				ListeningManager.enforceInitialization();
			}
		});
        Consciousness.ofSunny();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        speaking.say("Sunny initialized");
// not saving file and broken onNewCommand
//        "grammarForListening",
//                "recognizedWords",
//                "commandsSource",

//        memory.fileController("grammarForListening", FileOperators.SAVE);
//        memory.fileController("recognizedWords", FileOperators.SAVE);
//        memory.fileController("commandsSource", FileOperators.SAVE);
        System.out.println("all good ?");
    }

    public static ListeningManager getListener() {
        return listening;
    }

    public static SpeakingAdapter getSpeaking() {
        return speaking;
    }

    public static BasicKnowledge getBknowledge() {
        return bknowledge;
    }

	public static SunnyMemory getMemory() {
		return memory;
	}

	//executeInWT = executeInWorkingThread
    public static void executeInWT(Runnable workingThread)
    {
        if(defaultExecutor.isShutdown())
        {
            System.err.println("No executor");
        }
        defaultExecutor.execute(workingThread);
    }

    public static ExecutorService getPersistenExecutor()
    {
        return persistenExecutor;
    }
    
    public static ExecutorService getExecutor()
    {
        return defaultExecutor;
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
        if(object instanceof BasicKnowledge)
        {
            bknowledge = (BasicKnowledge) object;
            System.out.println("JLyc \"basic knowledge initialized\"");
            return;
        }
        if(object instanceof EnvironmentOfOS)
        {
            environmentOfOS = (EnvironmentOfOS) object;
            System.out.println("JLyc \"environment initialized\"");
            return;
        }
        if(object instanceof Consciousness)
        {
            consciousness = (Consciousness) object;
            System.out.println("JLyc \"consciousness initialized\"");
            return;
        }
		if(object instanceof SunnyMemory)
		{
			memory = (SunnyMemory) object;
			System.out.println("JLyc \"memory initialized\"");
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


}
