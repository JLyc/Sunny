package neural_center.listening;

import neural_center.initialization.BasicKnowledge;
import neural_center.initialization.SunnyInitialization;
import neural_center.speeking.SunnyVoice;
import run_application.SunnyComandProcess;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class SunnyListen extends Thread
{
	
	private ConfigurationManager cm;
	private Recognizer recognizer;
	private Microphone microphone;
	private BasicKnowledge bk = new BasicKnowledge();
	private SunnyVoice sunnyVoice = SunnyInitialization.getVoice();
	
	public SunnyListen()
	{
		//		cm = loadEnviromentalCorrectConfig();
		//		cm = new ConfigurationManager(SunnyListen.class.getResource("sunny.config.xml"));
		//		if (cm == null)
		//		{
		//			sunnyVoice.say("Error with listening device");
		//			System.exit(0);
		//		}
		//
		//		recognizer = (Recognizer) cm.lookup("recognizer");
		//		recognizer.allocate();
		//		microphone = (Microphone) cm.lookup("microphone");
		//		if (!microphone.startRecording())
		//		{
		//			System.err.println("Cannot start microphone.");
		//			recognizer.deallocate();
		//			return;
		//		}
		
		cm = new ConfigurationManager(SunnyListen.class.getResource("sunny_linux.config.xml"));
		recognizer = (Recognizer) cm.lookup("recognizer");
		recognizer.allocate();
		microphone = (Microphone) cm.lookup("microphone");
		if (!microphone.startRecording())
		{
			System.err.println("Cannot start microphone.");
			recognizer.deallocate();
			return;
		}
	}
	
	private ConfigurationManager loadEnviromentalCorrectConfig()
	{
		if (bk.getRecoghnizedEnviroment().equalsIgnoreCase("linux"))
		{
			//			return new ConfigurationManager(SunnyListen.class.getResource("sunny_linux.config.xml"));
			return new ConfigurationManager(SunnyListen.class.getResource("sunny.config.xml"));
		}
		else if (bk.getRecoghnizedEnviroment().equalsIgnoreCase("windows"))
		{
			return new ConfigurationManager(SunnyListen.class.getResource("sunny.config.xml"));
		}
		else if (bk.getRecoghnizedEnviroment().equalsIgnoreCase("xos"))
		{
			System.err.println("not implemented yet");
		}
		else
		{
			System.err.println("not recongnized os architecture");
		}
		return null;
	}
	
	@Override
	public void run()
	{
		super.run();
		
		try
		{
			do
			{
				System.out.println("listenning");
				Result result = recognizer.recognize();
				if (result != null)
				{
					String resultText = result.getBestFinalResultNoFiller();
					System.out.println(resultText);
					if (isValidCommand(resultText))
					{
						CommandCrate command = new CommandCrate(resultText);
						SunnyInitialization.getVoice().say(command.getModifier()+"ing " + command.getName());
						new SunnyComandProcess(command);
					}
				}
				else
				{
					System.out.println("I can't hear what you said.");
				}
			}
			while (true);
		}
		finally
		{
			recognizer.deallocate();
		}
	}
	
	private boolean isValidCommand(String resultText)
	{
		if (resultText.matches(".*sunny.+"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
