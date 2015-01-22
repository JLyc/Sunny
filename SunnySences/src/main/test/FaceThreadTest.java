import org.junit.Test;
import ui_fx.FaceThread;

import java.util.concurrent.TimeUnit;

/**
 * Created by JLyc on 21. 1. 2015.
 * Working
 */
public class FaceThreadTest {

    @Test
    public void testShowFace()
    {
        FaceThread ft = FaceThread.getInstance();
        ft.start();
        try { //hold so can be shown
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
