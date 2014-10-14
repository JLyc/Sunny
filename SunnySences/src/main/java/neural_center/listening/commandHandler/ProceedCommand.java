package neural_center.listening.commandHandler;

import neural_center.listening.commandHandler.run_application.SunnyCommandProcess;

/**
 * Created by socha on 13.10.2014.
 */
public class ProceedCommand implements Runnable{
    private String command;

    public ProceedCommand(String recordedCommand) {
        this.command = recordedCommand;
        Thread currentThread = new Thread(this);
        currentThread.setName("ProceedCommand "+recordedCommand);
        currentThread.start();
    }

    @Override
    public void run() {
        if(command.contains("sunny"))
        {
            new SunnyCommandProcess(new CommandCrate(command));
        }
    }
}
