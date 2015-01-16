package neural_center.listening.listeningAPI;

import neural_center.initialization.StaticBlockExecution;
import neural_center.listening.ListeningManager;

/**
 * Created by socha on 14.10.2014.
 */
public interface ListenerInterface extends Runnable, StaticBlockExecution{
    public void deinitializeListener();
    public void initializeListener(ListeningManager instance);
    public float getVersion();
}
