package neural_center.speaking.speakingAPI;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FTTKevinVoice implements SpeakingInterface {
    private static final String SUNNY = "kevin16";
    private static final float VERSION = 1.0f;

    private VoiceManager voiceManager;
    private Voice voice;

    public FTTKevinVoice() {
        try {
            voiceManager = VoiceManager.getInstance();
            boolean a = voiceManager.contains("kevin16");
            Voice[] b = voiceManager.getVoices();
            voice = voiceManager.getVoice(SUNNY);
            voice.allocate();
            say("");
//            registerTo.setSourceForAdapter(this);
        } catch (Exception e) {
            System.err.println("JLyc \"Speaking class not loaded can't be used\"");
            e.printStackTrace();
        }
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
