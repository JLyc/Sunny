import neural_center.listening.commandHandler.ReceivedCommand;
import org.junit.Test;

/**
 * Created by JLyc on 22. 1. 2015.
 * Test
 *
 * {@linkplain }
 */
public class ReceivedCommandTest {

    @Test
    public void testIt() {
        try {
            ReceivedCommand.updateKnowingWords();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ReceivedCommand("sunny show note pad");
    }
}
