import neural_center.initialization.BasicKnowledge;
import neural_center.initialization.EnvironmentOfOS;
import neural_center.memory.Memory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by sochaa on 20. 1. 2015.
 */
public class BasicKnowledgeTest {

    BasicKnowledge basicKnowledgeInstance;

    @Before
    public void testDependentClass(){
        EnvironmentOfOS.getInstance();
        Memory.getInstance();
        basicKnowledgeInstance = BasicKnowledge.getInstance();
    }

    @Test
    public void successfulDaleyTest() {
        assertNotNull(basicKnowledgeInstance.get("grammarForListening"));
    }
}
