import neural_center.initialization.EnvironmentOfOS;
import neural_center.listening.ListeningManager;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

/**
 * Created by JLyc on 21. 1. 2015.
 * Working
 */
public class ListeningManagerTest {

    @Before
    public void checkIfAllGood(){
        URL url = ListeningManager.class.getClassLoader().getResource(EnvironmentOfOS.getInstance().getProperties("listeningConfig"));
        assertNotNull(url);
    }

    @Test
    public void testListeningManager(){
        ListeningManager.getInstance();
        try {//keep test alive
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
