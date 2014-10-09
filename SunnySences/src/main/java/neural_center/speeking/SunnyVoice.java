package neural_center.speeking;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class SunnyVoice
{
	private static final String SUNNY = "kevin16";
	private VoiceManager voiceManager;
	private Voice voice;
	
	public SunnyVoice()
	{
		voiceManager = VoiceManager.getInstance();
		voice = voiceManager.getVoice(SUNNY);
		voice.allocate();
		voiceTest();
	}
	
	private void voiceTest()
	{
		voice.speak("Hi Sunny is here");
	}
	
	public synchronized void say(String text)
	{
		voice.speak(text);
	}
	
	public void deallocate()
	{
		voice.deallocate();
	}
}
