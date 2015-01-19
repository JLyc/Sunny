package neural_center.listening.commandHandler;

public class NoValidCommandException extends Exception
{
	private static final long serialVersionUID = -6201890437604097667L;
	
	@Override
	public String getMessage()
	{
		String message = super.getMessage()+"\\nno valid command";
		return message;
	}
}
