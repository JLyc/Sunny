package neural_center.speaking;

import neural_center.initialization.SunnySencesInterface;

/**
 * Created by socha on 10.10.2014.
 */
public interface SpeakingInterface extends SunnySencesInterface {

    public void say(String sayText) throws Exception;
}
