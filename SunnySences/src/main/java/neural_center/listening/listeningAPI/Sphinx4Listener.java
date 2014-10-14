package neural_center.listening.listeningAPI;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import neural_center.initialization.EnvironmentOfOS;
import neural_center.initialization.SunnyInitialization;
import neural_center.listening.ListeningManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sphinx4Listener extends Thread implements ListenerInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sphinx4Listener.class);

    private static final Sphinx4Listener INSTANCE = new Sphinx4Listener();
    private ListeningManager instanceOfListeningManager;
    private final float version = 1.0f;

    private ConfigurationManager cm = new ConfigurationManager(Sphinx4Listener.class.getResource(EnvironmentOfOS.getProperties("listeningConfig")));
    private Recognizer recognizer;
    private Microphone microphone;

    private boolean runThread = true;

    private Sphinx4Listener() {
        ListeningManager.requestRegistration(INSTANCE);
    }

    @Override
    public void initializeListener(ListeningManager instance) {
        if (cm != null) {
            LOGGER.error("Configuration Manger failed to load");
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
        this.instanceOfListeningManager = instance;
        this.setName("Listening Thread");
        this.run();
    }

    @Override
    public void deinitializeListener() {
        runThread = false;
    }

    @Override
    public float getVersion() {
        return version;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (runThread) {
                doListening();
            }
        } finally {
            SunnyInitialization.getSpeaking().say("I stop listening");
            recognizer.deallocate();
            ListeningManager.requestRegistration(INSTANCE);
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

