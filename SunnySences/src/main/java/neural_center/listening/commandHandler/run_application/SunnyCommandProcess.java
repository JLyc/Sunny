package neural_center.listening.commandHandler.run_application;

import neural_center.initialization.SunnyInitialization;
import neural_center.listening.commandHandler.CommandCrate;

import java.io.IOException;
import java.util.ArrayList;

public class SunnyCommandProcess
{
	private ArrayList<ArrayList<String>> loadedCommands = SunnyInitialization.getBknowledge().getCommands();
	private static EnvironmentCommandProperties envProp = new EnvironmentCommandProperties();
	private boolean isSudo = false;
	
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
			if(loadedCommands.get(i).get(1).equalsIgnoreCase(name)&&(Integer.parseInt(loadedCommands.get(i).get(2))==(modifierNumber)))
			{
				command = fillCommandsVariables(loadedCommands.get(i).get(3));
				break;
			}
		}
		return command;
	}

    private String fillCommandsVariables(String command) {
        if(command.matches(".*%user%.*"))
        {
            command.replace("%user%", envProp.getCommandUser());
        }
        if(command.matches(".*things to do with it.*"))
        {
            command.replace("%things to do with it%", envProp.getCommandExecutor());
        }
        if(command.contains("sudo"))
        {
            isSudo = true;
        }
        return command;
    }

	private boolean executeCommand(String command)
	{
		try
		{
			Process p = new ProcessBuilder(envProp.getCommandExecutor(), envProp.getCommandModifier(), command).start();
			if(isSudo)
			{
				p.getOutputStream().write((envProp.getSudo()+"\n").getBytes());
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
	
	@Deprecated
	private void cmdCommand(String whatToDo, String application)
	{
		Process p;
		try
		{
			p = Runtime.getRuntime().exec("cmd /c " + whatToDo + " " + application);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
