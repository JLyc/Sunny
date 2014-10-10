package neural_center.speeking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by socha on 10.10.2014.
 */
public class SpeakingAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpeakingAdapter.class);
    private static final SpeakingAdapter INSTANCE = new SpeakingAdapter();
    private static SpeakingInterface speakingSource;
    private static float currentVersion = 0;

    public static void setSpeakingSource(SpeakingInterface speakingSourceInstance)
    {
        if(speakingSourceInstance.getVersion() > currentVersion)
        {
            speakingSource = speakingSourceInstance;
            currentVersion = speakingSource.getVersion();
        }
    }

    public static SpeakingAdapter getInstance()
    {
        return INSTANCE;
    }

    public void say(String text) {
        try {
            speakingSource.say(text);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
            // TODO walidate output of error msgs
        }
    }

}
