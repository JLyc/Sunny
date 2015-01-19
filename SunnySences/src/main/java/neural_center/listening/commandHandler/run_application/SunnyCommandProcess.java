package neural_center.listening.commandHandler.run_application;

import neural_center.initialization.EnvironmentOfOS;
import neural_center.initialization.SunnyInitialization;
import neural_center.listening.commandHandler.CommandCrate;

import java.io.IOException;
import java.util.ArrayList;

public class SunnyCommandProcess
{
	private ArrayList<ArrayList<String>> loadedCommands = new ArrayList<>();
	private boolean isAdmin = false;
	
	public SunnyCommandProcess(CommandCrate commandCreate)
	{
		String command = buildCommand(commandCreate.getName(), commandCreate.getModifierNumber());
		if(!executeCommand(command))
            SunnyInitialization.getSpeaking().say("command do not executed");
	}

	private String buildCommand(String name, int modifierNumber)
	{
		String command = "";
		
		for (int i = 0; i < loadedCommands.size(); i++)
		{
			if(loadedCommands.get(i).get(0).equalsIgnoreCase(name)&&(Integer.parseInt(loadedCommands.get(i).get(1))==(modifierNumber)))
			{
				command = fillCommandsVariables(loadedCommands.get(i).get(2));
				break;
			}
		}
		return command;
	}

    private String fillCommandsVariables(String command) {
        if(command.matches(".*%user%.*"))
        {
            command.replace("%user%", EnvironmentOfOS.getProperties("user"));
        }
        if(command.matches(".*things to do with it.*"))
        {
            command.replace("%things to do with it%", EnvironmentOfOS.getProperties("something"));
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
            Process p = new ProcessBuilder(EnvironmentOfOS.getProperties("commandExecutor"), EnvironmentOfOS.getProperties("executorParameter"), command).start();
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
