package neural_center.speaking;

import neural_center.speaking.speakingAPI.FTTKevinVoice;
import neural_center.speaking.speakingAPI.SpeakingInterface;

/**
 * Created by socha on 10.10.2014.
 */
public final class SpeakingAdapter {
    private static SpeakingAdapter INSTANCE;
    private static SpeakingInterface speakingSource;

    private static float currentVersion = 0f;

    private int testAttempts = 3;

    private SpeakingAdapter() {
        speakingSource = FTTKevinVoice.init();
        setSourceForAdapter(speakingSource);
    }

    public void setSourceForAdapter(SpeakingInterface speakingSource) {
        if ((speakingSource.getVersion() > currentVersion) &&
                workingTest(speakingSource)) {
            this.speakingSource = speakingSource;
            this.currentVersion = speakingSource.getVersion();
        }
    }

    public void say(String text) {
        try {
            speakingSource.say(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean workingTest(SpeakingInterface newSpeakingSource) {
        for(int attempts=0;attempts<testAttempts; attempts++) {
            try {
                newSpeakingSource.say("");
            } catch (Exception e) {
                System.err.println("JLyc \"silenceTestFailed\"");
                return false;
            }
        }
        return true;
    }

    public static SpeakingAdapter getInstance() {
        if (INSTANCE == null) INSTANCE = new SpeakingAdapter();
        return INSTANCE;
    }
}
