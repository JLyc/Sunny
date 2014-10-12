package neural_center.speaking;

import neural_center.initialization.SunnySencesAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by socha on 10.10.2014.
 */
public class SpeakingAdapter extends SunnySencesAdapter<SpeakingInterface> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpeakingAdapter.class);

    public void say(String text) {
        try {
            sourceForAdapter.say(text);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
            // TODO validate output of error msgs
        }
    }

    @Override
    protected boolean workingTest(SpeakingInterface sourceForAdaper) {
        LOGGER.debug("Silence test with empty string to say");
        try {
            sourceForAdaper.say("");
        } catch (Exception e) {
            System.err.println("silenceTestFailed");
            return false;
        }
        return true;
    }
}
