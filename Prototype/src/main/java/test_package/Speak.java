package test_package;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * Created by JLyc on 30. 11. 2014.
 */
public class Speak {

    public static void main(String[] args) {
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        System.out.println(voice);
        System.out.println(VoiceManager.getInstance().getVoices().length);
    }

}
