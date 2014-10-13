package neural_center.listening.listeningAPI;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import neural_center.initialization.BasicKnowledge;
import neural_center.initialization.SunnyInitialization;
import neural_center.listening.ListeningInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sphinx4Listener extends Thread implements ListeningInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sphinx4Listener.class);

    private static final Sphinx4Listener INSTANCE = new Sphinx4Listener();
    private static final float version = 4.0f;

    private String recordedCommand = DEFAULT_COMMAND;

    private ConfigurationManager cm = loadEnvironmentalCorrectConfig();
    private Recognizer recognizer;
    private Microphone microphone;

    static
    {
        LOGGER.debug("initializing Sphinx4Listener");
        INSTANCE.start();
        SunnyInitialization.getListening().setSourceForAdapter(INSTANCE);
    }

    @Override
    public float getVersion() {
        return version;
    }

    private Sphinx4Listener() {
        if(cm != null) {
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
        this.setName("Listening Thread");
    }

    private ConfigurationManager loadEnvironmentalCorrectConfig() {
        BasicKnowledge bk = SunnyInitialization.getBknowledge();
        if (bk.getRecoghnizedEnviroment().equalsIgnoreCase("linux")) {
            return new ConfigurationManager(Sphinx4Listener.class.getResource("configs/sunny_linux.config.xml"));
        } else if (bk.getRecoghnizedEnviroment().equalsIgnoreCase("windows")) {
            return new ConfigurationManager(Sphinx4Listener.class.getResource("configs/sunny.config.xml"));
        } else if (bk.getRecoghnizedEnviroment().equalsIgnoreCase("xos")) {
            System.err.println("not implemented yet");
        } else {
            System.err.println("not recongnized os architecture");
        }
        return null;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                doListening();
            }
        } finally {
            SunnyInitialization.getSpeaking().say("I stop listening because of internal error");
            recognizer.deallocate();
        }
    }

    private void doListening() {
        Result result = recognizer.recognize();
        if (result != null) {
            recordedCommand = result.getBestFinalResultNoFiller();
            SunnyInitialization.getListening().onNewCommand(recordedCommand,INSTANCE);
        }
    }
}
