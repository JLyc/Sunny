package neural_center.initialization;

/**
 * Created by jlyc on 12.10.2014.
 */
public abstract class SunnySencesAdapter<T extends SunnySencesInterface> {

    protected T sourceForAdapter;
    protected float currentVersion = 0;

    public void setSourceForAdaper(T sourceForAdapter) {
        if ((sourceForAdapter.getVersion() > currentVersion) &&
                workingTest(sourceForAdapter)) {
            this.sourceForAdapter = sourceForAdapter;
            this.currentVersion = sourceForAdapter.getVersion();
        }
    }

    protected abstract boolean workingTest(T sourceForAdapter);

}
