package neural_center.listening.listeningAPI;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.jsgf.JSGFGrammar;
import edu.cmu.sphinx.jsgf.JSGFGrammarException;
import edu.cmu.sphinx.jsgf.JSGFGrammarParseException;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import neural_center.initialization.EnvironmentOfOS;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;

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
        JSGFGrammar jsf = (JSGFGrammar) cm.lookup("jsgfGrammar");
//        jsf.newProperties();

        jsf.setBaseURL(FileSystems.getDefault().getPath(System.getProperty("user.dir"), "Brain", EnvironmentOfOS.getProperties("os")).toUri().toURL());

        jsf.loadJSGF("sunny_windows_test");
//        jsf.commitChanges();
        recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();
        microphone = (Microphone) cm.lookup("microphone");

        if (!microphone.startRecording()) {
            System.err.println("Cannot start microphone.");
            recognizer.deallocate();
            return;
        }
    }

    public static void main(String[] args) throws IOException, JSGFGrammarParseException, JSGFGrammarException {
        EnvironmentOfOS.enforceInitialization();
        SphinxFun sf = new SphinxFun(SphinxFun.class.getResource(EnvironmentOfOS.getProperties("listeningConfig")));
    }
}
