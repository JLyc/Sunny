package neural_center.listening.listeningAPI;

import neural_center.listening.ListeningManager;

/**
 * Created by socha on 14.10.2014.
 */
public interface ListenerInterface extends Runnable {
    public void deinitializeListener();
    public void initializeListener(ListeningManager instance);
    public float getVersion();
}
