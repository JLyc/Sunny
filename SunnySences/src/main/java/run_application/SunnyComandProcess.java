package run_application;

import java.io.IOException;
import java.util.ArrayList;

import neural_center.initialization.SunnyInitialization;
import neural_center.listening.CommandCrate;

public class SunnyComandProcess
{
	private ArrayList<ArrayList<String>> loadedCommands = SunnyInitialization.getBknowledge().getCommands();
	private static EnviromentCommandProperties envProp = new EnviromentCommandProperties(System.getProperty("os.name"));
	//	private ArrayList<ArrayList<String>> loadedCommands = new BasicKnowledge().getCommands();
	private boolean isSudo = false;
	
	public SunnyComandProcess(CommandCrate commandCreate)
	{
		String command =  buildCommand(commandCreate.getName(), commandCreate.getModifierNumber());
		executeCommand(command);
	}
	
	
	
	private String buildCommand(String name, int modifierNumber)
	{
		String command = "";
		
		for (int i = 0; i < loadedCommands.size(); i++)
		{
			if(loadedCommands.get(i).get(1).equalsIgnoreCase(name)&&(Integer.parseInt(loadedCommands.get(i).get(2))==(modifierNumber)))
			{
				command = loadedCommands.get(i).get(3);
				if(command.matches(".+%user%.+"))
				{
					command.replace("%user%", envProp.getCommandUser());
				}
				isSudo = command.contains("sudo");
				break;
			}
		}
		return command;
	}
	
	@Deprecated
	public static void main(String args[])
	{
		String command = "Sunny Run Fire Fox";
		CommandCrate feedback = new CommandCrate(command);
		new SunnyComandProcess(feedback);
	}
	
	
	private boolean executeCommand(String command)
	{
		
		try
		{
			Process p = new ProcessBuilder(envProp.getCommandExecutor(), envProp.getCommandModifier(), command).start();
			if(isSudo)
			{
				p.getOutputStream().write((envProp.hardCoded()+"\n").getBytes());
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
