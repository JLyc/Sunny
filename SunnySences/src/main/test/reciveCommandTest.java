import neural_center.listening.commandHandler.reciveCommand;
import org.junit.Test;

/**
 * Created by JLyc on 22. 1. 2015.
 * Test
 */
public class reciveCommandTest {

    @Test
    public void testIt() {
        try {
            reciveCommand.updateKnowingWords();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new reciveCommand("sunny run email");
    }
}
