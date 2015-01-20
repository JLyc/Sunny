import neural_center.initialization.EnvironmentOfOS;
import neural_center.memory.SunnyMemory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by JLyc on 1/18/2015.
 */
public class SunnyMemoryTest {

	@Before
	public void testDependentClass(){
		EnvironmentOfOS.enforceInitialization();

	}

	@Test//(expected=FileNotFoundException.class)
	public void faultTest() {
		assertNull(SunnyMemory.getPathInMemory("Action"));
	}

	@Test
	public void testAllClass(){
		SunnyMemory.enforceInitialization();
	}

	@Test
	public void successfulTest() {
		SunnyMemory.getPathInMemory("Action");
	}
}
