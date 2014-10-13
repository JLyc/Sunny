package neural_center.listening;

import neural_center.initialization.SunnySencesAdapter;
import neural_center.listening.commandHandler.ProceedCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jlyc on 12.10.2014.
 */
public class ListeningAdapter extends SunnySencesAdapter<ListeningInterface> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListeningAdapter.class);

    private static final ListeningAdapter INSTANCE = new ListeningAdapter();

    private ListeningAdapter() {}

    public static ListeningAdapter getInstance()
    {
        return INSTANCE;
    }

    @Override
    protected boolean workingTest(ListeningInterface sourceForAdapter) {
        return true;
    }

    public void onNewCommand(String recordedSound, ListeningInterface source)
    {
        if(currentVersion == source.getVersion())
            new ProceedCommand(recordedSound);
    }
}
