package neural_center.listening.listeningAPI;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import neural_center.initialization.EnvironmentOfOS;
import neural_center.initialization.SunnyInitialization;
import neural_center.listening.ListeningManager;

import java.net.URL;

public class Sphinx4Listener implements Runnable {

    private ListeningManager instanceOfListeningManager;
    private final float version = 1.0f;

    private ConfigurationManager cm;
    private Recognizer recognizer;
    private Microphone microphone;

    private boolean runThread = true;

    public Sphinx4Listener() {
        this(Sphinx4Listener.class.getResource(EnvironmentOfOS.getProperties("listeningConfig")));
    }

    public Sphinx4Listener(URL url) {
        cm = new ConfigurationManager(url);
        initializeListener();
    }

    private void initializeListener() {
        if (cm == null) {
            System.err.println("JLyc \"Configuration Manger failed to load\"");
            return;
        }

        recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();
        microphone = (Microphone) cm.lookup("microphone");

        if (!microphone.startRecording()) {
            System.err.println("Cannot start microphone.");
            recognizer.deallocate();
            return;
        }
    }

    public void deinitializeListener() {
        runThread = false;
    }

    @Override
    public void run() {
        try {
            instanceOfListeningManager = SunnyInitialization.getListener();
            while (runThread) {
                doListening();
            }
        } finally {
            SunnyInitialization.getSpeaking().say("I stop listening");
            recognizer.deallocate();
        }
    }

    private void doListening() {
        Result result = recognizer.recognize();
        if (result != null) {
            String recordedCommand = result.getBestFinalResultNoFiller();
            instanceOfListeningManager.onNewCommand(recordedCommand);
        }
    }
}

