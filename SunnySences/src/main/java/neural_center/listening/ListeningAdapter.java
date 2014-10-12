package neural_center.listening;

import neural_center.initialization.SunnySencesAdapter;

/**
 * Created by jlyc on 12.10.2014.
 */
public class ListeningAdapter extends SunnySencesAdapter<ListeningInteface> {

    @Override
    protected boolean workingTest(ListeningInteface sourceForAdapter) {
        return true;
    }
}
