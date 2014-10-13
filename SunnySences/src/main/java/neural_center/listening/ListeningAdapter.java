package neural_center.listening;

import neural_center.initialization.SunnySencesAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jlyc on 12.10.2014.
 */
public class ListeningAdapter extends SunnySencesAdapter<ListeningInteface> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListeningAdapter.class);

    private static final ListeningAdapter INSTANCE = new ListeningAdapter();

    private ListeningAdapter() {}

    public static ListeningAdapter getInstance()
    {
        return INSTANCE;
    }

    @Override
    protected boolean workingTest(ListeningInteface sourceForAdapter) {
        return true;
    }


}
