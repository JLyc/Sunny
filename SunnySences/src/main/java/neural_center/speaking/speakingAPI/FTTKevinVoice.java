package neural_center.speaking.speakingAPI;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FTTKevinVoice implements SpeakingInterface {
    private static final String SUNNY = "kevin16";
    private static final float VERSION = 1.0f;

    private static VoiceManager voiceManager;
    private static Voice voice;

    private FTTKevinVoice() {

    }

    public static SpeakingInterface init() {
        try {
            voiceManager = VoiceManager.getInstance();
            voiceManager.contains("kevin16");
            voice = voiceManager.getVoice(SUNNY);
            voice.allocate();
        } catch (Exception e) {
            System.err.println("JLyc \"Speaking class not loaded can't be used\"");
            e.printStackTrace();
        }
            return new FTTKevinVoice();
    }

    private void deallocate() {
        voice.deallocate();
    }

    @Override
    public synchronized void say(String sayText) throws Exception {
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
