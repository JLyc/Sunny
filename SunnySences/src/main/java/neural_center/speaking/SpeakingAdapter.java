package neural_center.speaking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by socha on 10.10.2014.
 */
public class SpeakingAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpeakingAdapter.class);

    private static final SpeakingAdapter INSTANCE = new SpeakingAdapter();
    private static SpeakingInterface speakingSource;
    private static float currentVersion = 0f;

    private int testAttempts = 3;

    private SpeakingAdapter() {}

    public boolean setSourceForAdapter(SpeakingInterface speakingSource) {
        if ((speakingSource.getVersion() > currentVersion) &&
                workingTest(speakingSource)) {
            this.speakingSource = speakingSource;
            this.currentVersion = speakingSource.getVersion();
            return true;
        }
        return false;
    }

    public static SpeakingAdapter getInstance() {
        return INSTANCE;
    }

    protected boolean workingTest(SpeakingInterface newSpeakingSource) {
        LOGGER.debug("Silence test with empty string to say {} attempts", testAttempts);
        for(int attempts=0;attempts<testAttempts; attempts++) {
            try {
                newSpeakingSource.say("");
            } catch (Exception e) {
                System.err.println("silenceTestFailed");
                return false;
            }
        }
        return true;
    }

    public void say(String text) {
        try {
            speakingSource.say(text);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
            // TODO validate output of error msgs
        }
    }
}
