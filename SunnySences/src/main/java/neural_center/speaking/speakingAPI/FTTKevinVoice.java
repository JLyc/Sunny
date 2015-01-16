package neural_center.speaking.speakingAPI;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import neural_center.initialization.SunnyInitialization;


public class FTTKevinVoice implements SpeakingInterface {
    private static final FTTKevinVoice INSTANCE = new FTTKevinVoice();
    private static final String SUNNY = "kevin16";
    private static final float VERSION = 1.0f;
    private VoiceManager voiceManager;
    private Voice voice;

    private FTTKevinVoice() {
        try {
            voiceManager = VoiceManager.getInstance();
            voice = voiceManager.getVoice(SUNNY);
            voice.allocate();
            say("");
        } catch (Exception e) {
            System.err.println("JLyc \"Speaking class not loaded can't be used\"");
            e.printStackTrace();
        }
        SunnyInitialization.getSpeaking().setSourceForAdapter(INSTANCE);
    }

    private void deallocate() {
        voice.deallocate();
    }

    @Override
    public void say(String sayText) throws Exception {
        try {
            voice.speak(sayText);
        } catch (Exception e) {
            deallocate();
            throw new Exception("JLyc \"Can't execute speak ability\"");
        }
    }

    @Override
    public float getVersion() {
        return VERSION;
    }

}
