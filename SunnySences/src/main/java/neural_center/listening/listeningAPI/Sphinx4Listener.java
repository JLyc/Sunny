package neural_center.listening.listeningAPI;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import neural_center.initialization.Sunny;
import neural_center.listening.ListeningManager;

import java.net.URL;
public class Sphinx4Listener implements Runnable {
    private ListeningManager instanceOfListeningManager;

    private ConfigurationManager cm;
    private Recognizer recognizer;
    private Microphone microphone;

    private boolean runThread = true;

    public Sphinx4Listener() {
        this(Sphinx4Listener.class.getClass().getClassLoader().getResource(Sunny.getEnvironmentOfOS().getProperties("listeningConfig")));

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

        recognizer = cm.lookup("recognizer");
        recognizer.allocate();
        microphone = cm.lookup("microphone");

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
            while (runThread) {
                doListening();
            }
        } finally {
            Sunny.getSpeaking().say("I stop listening");
            recognizer.deallocate();
        }
    }

    private void doListening() {
        Result result = recognizer.recognize();
        if (result != null) {
            String recordedCommand = result.getBestFinalResultNoFiller();
            Sunny.getListening().onNewCommand(recordedCommand);
        }
    }
}

