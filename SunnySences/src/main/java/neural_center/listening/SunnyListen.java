package neural_center.listening;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import neural_center.initialization.BasicKnowledge;
import neural_center.initialization.SunnyInitialization;
import neural_center.speaking.SunnyVoice;

public class SunnyListen extends Thread {

    private ConfigurationManager cm;
    private Recognizer recognizer;
    private Microphone microphone;
    private BasicKnowledge bk = new BasicKnowledge();
    private SunnyVoice sunnyVoice = SunnyInitialization.getVoice();

    public SunnyListen() {
        //		cm = loadEnviromentalCorrectConfig();
        //		cm = new ConfigurationManager(SunnyListen.class.getResource("sunny.config.xml"));
        //		if (cm == null)
        //		{
        //			sunnyVoice.say("Error with listening device");
        //			System.exit(0);
        //		}
        //
        //		recognizer = (Recognizer) cm.lookup("recognizer");
        //		recognizer.allocate();
        //		microphone = (Microphone) cm.lookup("microphone");
        //		if (!microphone.startRecording())
        //		{
        //			System.err.println("Cannot start microphone.");
        //			recognizer.deallocate();
        //			return;
        //		}

        cm = new ConfigurationManager(SunnyListen.class.getResource("sunny_linux.config.xml"));
        recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();
        microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.err.println("Cannot start microphone.");
            recognizer.deallocate();
            return;
        }
    }

    private ConfigurationManager loadEnviromentalCorrectConfig() {
        if (bk.getRecoghnizedEnviroment().equalsIgnoreCase("linux")) {
            //			return new ConfigurationManager(SunnyListen.class.getResource("sunny_linux.config.xml"));
            return new ConfigurationManager(SunnyListen.class.getResource("sunny.config.xml"));
        } else if (bk.getRecoghnizedEnviroment().equalsIgnoreCase("windows")) {
            return new ConfigurationManager(SunnyListen.class.getResource("sunny.config.xml"));
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
            SunnyInitialization.getVoice().say("I stop listening because of internal error");
            recognizer.deallocate();
        }
    }

    private String doListening() {
        String resultText = "No recognized command";
        Result result = recognizer.recognize();
        if (result != null)
            resultText = result.getBestFinalResultNoFiller();
        return resultText;
    }
}
