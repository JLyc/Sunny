package neural_center.Speaking;

import neural_center.speaking.SpeakingAdapter;

/**
 * Created by jlyc on 12.10.2014.
 */
public class TestSpeaking {

    private static final String TEST_SENTENCE = "Sunny voice test succesfull";

    // TODO not working standlone lunch
    public static void main(String[] args) {
        try {
            SpeakingAdapter speakingAdapter = SpeakingAdapter.getInstance();
            speakingAdapter.say(TEST_SENTENCE);
        } catch (Exception ex) {
            System.err.println("test fail");
            ex.getStackTrace();
        }
        System.out.print("test succesfull");
    }

}
