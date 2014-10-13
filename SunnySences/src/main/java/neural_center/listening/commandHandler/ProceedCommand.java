package neural_center.listening.commandHandler;

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
        if(validateCommand(command))
        {
            CommandCrate transporter = new CommandCrate(command);


        }
    }

    private boolean validateCommand(String command) {
        return command.contains("sunny");
    }
}
