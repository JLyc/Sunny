package neural_center.speaking;

import neural_center.initialization.SunnyInitialization;
import neural_center.speaking.speakingAPI.FTTKevinVoice;
import neural_center.speaking.speakingAPI.SpeakingInterface;

/**
 * Created by socha on 10.10.2014.
 */
public final class SpeakingAdapter {
    private static final SpeakingAdapter INSTANCE;
    private static SpeakingInterface speakingSource;
    private static float currentVersion = 0f;

    private int testAttempts = 3;

    static
    {
        INSTANCE = new SpeakingAdapter();
        SunnyInitialization.setStateOkFor(INSTANCE);
        new FTTKevinVoice();
    }

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

    public void say(String text) {
        try {
//            long startTime = System.currentTimeMillis();
            //TODO run equalizer
            speakingSource.say(text);
            //TODO stop equalizer;
//            long finishTime = System.currentTimeMillis();
//            System.out.println("That took: " + (finishTime - startTime) + " ms");

        } catch (Exception e) {
            e.printStackTrace();
            // TODO validate output of error msgs
        }
    }

    public static void enforceInitialization(){}
}
