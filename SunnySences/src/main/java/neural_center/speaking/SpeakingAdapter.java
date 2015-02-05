package neural_center.speaking;

import neural_center.speaking.speakingAPI.FTTKevinVoice;
import neural_center.speaking.speakingAPI.SpeakingInterface;
import ui_fx.TestMove;
import uniqe_skills.PerformanceTest;

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
        System.out.println("Speaking \t\t\t load successful: " + workingTest(speakingSource));
        PerformanceTest.result();
    }

    public void setSourceForAdapter(SpeakingInterface speakingSource) {
        if ((speakingSource.getVersion() > currentVersion) &&
                workingTest(speakingSource)) {
            this.speakingSource = speakingSource;
            this.currentVersion = speakingSource.getVersion();
        }
    }
//    @Deprecated
    public void sayOld(String text) {
        try {
            speakingSource.say(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void say(String text) {
        try {
            TestMove.visauliseSay(text);
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

    public synchronized static SpeakingAdapter getInstance() {
        if (INSTANCE == null) INSTANCE = new SpeakingAdapter();
        return INSTANCE;
    }

    public static void main(String[] args) {
        try {
            TestMove.showFace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SpeakingAdapter sp = SpeakingAdapter.getInstance();
        sp.say("Hello");
        sp.say("Hello");
        sp.say("how");
        sp.say("are");
        sp.say("you");
        sp.say("i'm not synchronized it's sad");
//        sp.say("how are you today Andrew");
    }
}
