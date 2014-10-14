package neural_center.Speaking;

import neural_center.speaking.SpeakingAdapter;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by socha on 14.10.2014.
 */
public class TestSpeakingAdapter {

    @Test
    public void testGetInstance() {
        assertNotNull(SpeakingAdapter.getInstance());
    }

    @Test
    public void testSay()
    {
        try {
            SpeakingAdapter.getInstance().say("");
        }catch (Exception e)
        {
            fail();
        }
    }
}
