package neural_center.listening;

import java.util.ArrayList;
import java.util.Iterator;

import neural_center.initialization.SunnyInitialization;

public class CommandCrate
{
	private String name;
	private String modifier;
	private int modifierNumber;
	private String commandDescription;
	private String[] splitedCommand;
	
	ArrayList<String[]> parts = new ArrayList<>();
	
	public CommandCrate(String command)
	{
		try
		{
			splitedCommand = command.split("\\s");
			Iterator<String[]> partIter = makeParts().iterator();
			
			verifyCommand(partIter.next());
			resolveModifier(partIter.next());
			resolveName(partIter.next());
			if(partIter.hasNext())
			{
				resolveCommandDescripton(partIter.next());
			}
			
		}
		catch (NoValidCommandException e)
		{
			return;
		}
	}
	
	private void resolveCommandDescripton(String[] list)
	{
		for (int index = 3; index < splitedCommand.length; index++)
		{
			commandDescription += splitedCommand[index];
		}
	}
	
	private void resolveModifier(String[] list)
	{
		for (int index = 0; index < list.length; index++)
		{
			if (splitedCommand[1].equalsIgnoreCase(list[index]))
			{
				modifier = list[index];
				resolveModifierNumber();
				return;
			}
		}
		
	}
	
	private void resolveModifierNumber()
	{
		//		ArrayList<ArrayList<String>> wordPower = new BasicKnowledge().getWordPower();
		Iterator<?> wordPowerIterator = SunnyInitialization.getBknowledge().getWordPower().iterator();
		while (wordPowerIterator.hasNext())
		{
			ArrayList<String> mapedWordPower = (ArrayList<String>) wordPowerIterator.next();
			if(mapedWordPower.contains(modifier))
			{
				modifierNumber = Integer.parseInt(mapedWordPower.get(1));
				return;
			}
		}
	}
	
	private void resolveName(String[] list)
	{
		int possileNameIndex = 2;
		String possibleName = splitedCommand[possileNameIndex];
		while (true)
		{
			for (int index = 0; index < list.length; index++)
			{
				if (possibleName.equalsIgnoreCase(list[index]))
				{
					name = list[index];
					return;
				}
			}
			possibleName+=" "+splitedCommand[++possileNameIndex];
		}
	}
	
	private void verifyCommand(String[] list) throws NoValidCommandException
	{
		if ((list.length == 1) && splitedCommand[0].equalsIgnoreCase(list[0]))
		{
			return;
		}
		else
		{
			throw new NoValidCommandException();
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getModifier()
	{
		return modifier;
	}
	
	public int getModifierNumber()
	{
		return modifierNumber;
	}
	
	public String getCommandDescription()
	{
		return commandDescription;
	}
	
	private ArrayList<String[]> makeParts()
	{
		String command = "(Sunny) (Run | Run | Show | Start | Close | End | Turn off | Sleep | Volume | Mute | Un mute) (Fire Fox | Goggle Chrome | Email | Face Book | Team Speak | Computer | up | down | eclipse)";
		String[] breakLinePart = command.substring(1, command.length() - 1).split("\\) \\(");
		for (String part : breakLinePart)
		{
			parts.add(part.split("\\s\\|\\s"));
		}
		return parts;
	}
	
	@Deprecated
	public static void main(String[] args)
	{
		String command = "Sunny Run Fire Fox";
		CommandCrate feedback = new CommandCrate(command);
		System.out.println(feedback.getCommandDescription());
		System.out.println(feedback.getModifier());
		System.out.println(feedback.getModifierNumber());
		System.out.println(feedback.getName());
	}
}
