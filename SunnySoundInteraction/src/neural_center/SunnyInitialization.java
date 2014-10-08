package neural_center;

import neural_center.listening.SunnyListen;
import neural_center.memory.SunnyMemory;
import neural_center.speeking.SunnyVoice;

public class SunnyInitialization
{
	private static SunnyVoice voice;
	private static SunnyListen listen;
	private static SunnyMemory memory;
	private static BasicKnowledge bknowledge;
	
	public static void main(String arg[]) throws NullPointerException
	{
		voice = new SunnyVoice();
		listen = new SunnyListen();
		memory = new SunnyMemory();
		bknowledge = new BasicKnowledge();
		if(testSunnyFunction())
		{
			throw new NullPointerException();
		}
		listen.start();
	}
	
	public static SunnyListen getListen()
	{
		return listen;
	}
	
	public static SunnyVoice getVoice()
	{
		return voice;
	}
	
	public static SunnyMemory getMemory()
	{
		return memory;
	}
	
	public static BasicKnowledge getBknowledge()
	{
		return bknowledge;
	}
	
	private static boolean testSunnyFunction()
	{
		boolean okCheck = false;
		
		if(listen==null)
		{
			System.err.println("no listen initialized");
			okCheck = true;
		}
		if(voice==null)
		{
			System.err.println("no voice initialized");
			okCheck = true;
		}
		if(memory==null)
		{
			System.err.println("no memory initialized");
			okCheck = true;
		}
		if(bknowledge==null)
		{
			System.err.println("no basic knowledge initialized");
			okCheck = true;
		}
		
		return okCheck;
	}
}
