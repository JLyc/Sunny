package neural_center.listening.commandHandler;

import neural_center.listening.commandHandler.run_application.SunnyCommandProcess;

/**
 * Created by socha on 13.10.2014.
 */
public class ProceedCommand implements Runnable{
    private String command;

    public ProceedCommand(String recordedCommand) {
        this.command = recordedCommand.toLowerCase();
    }

    @Override
    public void run() {
        if(command.contains("sunny"))
        {
            new SunnyCommandProcess(new CommandCrate(command));
        }
    }
}
