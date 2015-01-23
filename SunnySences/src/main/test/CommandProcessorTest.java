import org.junit.Test;

import java.io.IOException;

/**
 * Created by JLyc on 23. 1. 2015.
 */
public class CommandProcessorTest {

    @Test
    public void testCommand()
    {
        try
        {
            Process p = new ProcessBuilder("cmd", "/c", "notepad.exe").start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
