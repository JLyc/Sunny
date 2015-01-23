package neural_center.listening.commandHandler.run_application;

import java.io.IOException;

/**
 * Created by JLyc on 23. 1. 2015.
 */
public class CommandProcessor {

    public static void executeCommand(String ... commandProperties)
    {
        try
        {
            Process p = new ProcessBuilder(commandProperties).start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
