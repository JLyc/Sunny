package neural_center.listening.listeningAPI;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.jsgf.JSGFGrammarException;
import edu.cmu.sphinx.jsgf.JSGFGrammarParseException;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.util.props.ConfigurationManager;

import java.io.IOException;
import java.net.URL;

/**
 * Created by sochaa on 17. 12. 2014.
 */
public class SphinxFun {
    private ConfigurationManager cm;
    private Recognizer recognizer;
    private Microphone microphone;

    public SphinxFun(URL url) throws IOException, JSGFGrammarParseException, JSGFGrammarException {
        cm = new ConfigurationManager(url);
        initializeListener();
    }

    private void initializeListener() throws IOException, JSGFGrammarParseException, JSGFGrammarException {
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
}
