package neural_center.speaking.speakingAPI;

/**
 * Created by socha on 10.10.2014.
 */
public interface SpeakingInterface {

    public void say(String sayText) throws Exception;
    public float getVersion();
}
