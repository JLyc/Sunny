package neural_center.speaking.speakingAPI;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import neural_center.initialization.SunnyInitialization;
import neural_center.speaking.SpeakingInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FTTKevinVoice implements SpeakingInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(FTTKevinVoice.class);

    private static final FTTKevinVoice INSTANCE = new FTTKevinVoice();
    private static final String SUNNY = "kevin16";
    private static final float VERSION = 1.0f;
    private VoiceManager voiceManager;
    private Voice voice;

    static {
        LOGGER.debug("Initializing {}", FTTKevinVoice.class);
        SunnyInitialization.getSpeaking().setSourceForAdapter(INSTANCE);
    }


    private FTTKevinVoice() {
        voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(SUNNY);
        voice.allocate();
    }

    @Override
    public void say(String sayText) throws Exception {
        try {
            voice.speak(sayText);
        } catch (Exception e) {
            deallocate();
            throw new Exception("Can't execute speak abillity");
        }
    }

    @Override
    public float getVersion() {
        return VERSION;
    }

    private void deallocate() {
        voice.deallocate();
    }
}
