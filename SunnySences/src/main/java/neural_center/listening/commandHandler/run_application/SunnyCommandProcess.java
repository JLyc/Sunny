package neural_center.listening.commandHandler.run_application;

import neural_center.initialization.EnvironmentOfOS;
import neural_center.listening.commandHandler.CommandCrate;
import neural_center.speaking.SpeakingAdapter;

import java.io.IOException;

public class SunnyCommandProcess
{
	private boolean isAdmin = false;

	public SunnyCommandProcess(CommandCrate commandCreate)
	{
		String command = buildCommand(commandCreate.getName(), commandCreate.getModifierNumber());
		if(!executeCommand(command))
            SpeakingAdapter.getInstance().say("command do not executed");
	}

	private String buildCommand(String name, int modifierNumber)
	{
		String command = "";

//		for (ArrayList<String> loadedCommand : loadedCommands) {
//			if (loadedCommand.get(0).equalsIgnoreCase(name) && (Integer.parseInt(loadedCommand.get(1)) == (modifierNumber))) {
//				command = fillCommandsVariables(loadedCommand.get(2));
//				break;
//			}
//		}
		return command;
	}

    private String fillCommandsVariables(String command) {
        if(command.matches(".*%user%.*"))
        {
            command = command.replace("%user%", EnvironmentOfOS.getInstance().getProperties("user"));
        }
        if(command.matches(".*things to do with it.*"))
        {
			command = command.replace("%things to do with it%", EnvironmentOfOS.getInstance().getProperties("something"));
        }
        if(command.contains("sudo"))
        {
            isAdmin = true;
        }
        return command;
    }

	private boolean executeCommand(String command)
	{
		try
		{
            System.out.println(command);
            Process p = new ProcessBuilder(EnvironmentOfOS.getInstance().getProperties("commandExecutor"), EnvironmentOfOS.getInstance().getProperties("executorParameter"), command).start();
			if(isAdmin)
			{
				p.getOutputStream().write(("171189"+"\n").getBytes());
				p.getOutputStream().flush();
			}
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}

	}

}
