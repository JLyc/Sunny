package neural_center.speaking;

import neural_center.initialization.SunnySencesAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by socha on 10.10.2014.
 */
public class SpeakingAdapter extends SunnySencesAdapter<SpeakingInterface> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpeakingAdapter.class);

    private static final SpeakingAdapter INSTANCE = new SpeakingAdapter();
    private int testAttempts = 3;

    private SpeakingAdapter() {}

    public static SpeakingAdapter getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean workingTest(SpeakingInterface sourceForAdaper) {
        LOGGER.debug("Silence test with empty string to say {} attempts", testAttempts);
        for(int attempts=0;attempts<testAttempts; attempts++) {
            try {
                sourceForAdaper.say("");
            } catch (Exception e) {
                System.err.println("silenceTestFailed");
                return false;
            }
        }
        return true;
    }

    public void say(String text) {
        try {
            sourceForAdapter.say(text);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
            // TODO validate output of error msgs
        }
    }
}
