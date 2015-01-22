import neural_center.speaking.SpeakingAdapter;
import org.junit.Test;

/**
 * Created by JLyc on 21. 1. 2015.
 * Working
 */
public class SpeakingAdapterTest {

    @Test
    public void testSpeakingAdapter(){
        SpeakingAdapter sa = SpeakingAdapter.getInstance();
        sa.say("Validate output by hearing");
    }
}
