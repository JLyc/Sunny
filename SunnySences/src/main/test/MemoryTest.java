import neural_center.initialization.EnvironmentOfOS;
import neural_center.memory.Memory;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by JLyc on 1/18/2015.
 */
public class MemoryTest {

	@Before
	public void testDependentClass(){
		EnvironmentOfOS.getInstance();
	}

	@Test
	public void testAllClass() {
		Memory memory;
		memory = Memory.getInstance();
		memory.getPathInMemory("Action");
	}
}
