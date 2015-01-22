import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by JLyc on 22. 1. 2015.
 * Working
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(
                EnvironmentOfOSTest.class,
                MemoryTest.class,
                BasicKnowledgeTest.class,
                SpeakingAdapterTest.class,
                ListeningManagerTest.class,
                FaceThreadTest.class
                );
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
