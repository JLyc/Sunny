import neural_center.initialization.BasicKnowledge;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


/**
 * Created by JLyc on 20. 1. 2015.
 * Working
 */
public class BasicKnowledgeTest {

    @Test
    public void successfulDaleyTest() {
        assertNotNull(BasicKnowledge.getInstance().get("grammarForListening"));
    }
}
