import neural_center.initialization.BasicKnowledge;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


/**
 * Created by sochaa on 20. 1. 2015.
 */
public class BasicKnowledgeTest {

    @Test
    public void successfulDaleyTest() {
        assertNotNull(BasicKnowledge.getInstance().get("grammarForListening"));
    }
}
