package neural_center.speaking.speakingAPI;

import neural_center.initialization.StaticBlockExecution;

/**
 * Created by socha on 10.10.2014.
 */
public interface SpeakingInterface extends StaticBlockExecution{

    public void say(String sayText) throws Exception;
    public float getVersion();
}
