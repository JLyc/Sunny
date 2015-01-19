import neural_center.initialization.EnvironmentOfOS;
import neural_center.memory.SunnyMemory;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by JLyc on 1/18/2015.
 */
public class SunnyMemoryTest {

	@Before
	public void testDependentClass(){
		EnvironmentOfOS.enforceInitialization();
	}

	@Test
	public void testAllClass(){
		SunnyMemory.enforceInitialization();
	}
}
