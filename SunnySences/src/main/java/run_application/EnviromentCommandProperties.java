package run_application;

public class EnviromentCommandProperties
{
	private String commandExecutor;
	private String commandModifier;
	private String commandUser;
	
	public EnviromentCommandProperties(String property)
	{
		if(property.equalsIgnoreCase("Linux"))
		{
			commandExecutor = "/bin/sh";
			commandModifier = "-c";
			commandUser = System.getProperty("user.name");
		}
		else if(property.equalsIgnoreCase("Windows"))
		{
			commandExecutor = "cmd";
			commandModifier = "/c";
			commandUser = "not implemented";
		}
	}
	
	public String getCommandExecutor()
	{
		return commandExecutor;
	}
	
	public String getCommandModifier()
	{
		return commandModifier;
	}
	
	public String getCommandUser()
	{
		return commandUser;
	}
	
	public String hardCoded()
	{
		return "171189";
	}
	
	
}
