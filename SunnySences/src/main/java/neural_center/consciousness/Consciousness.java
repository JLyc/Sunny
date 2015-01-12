package neural_center.consciousness;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sochaa on 3. 12. 2014.
 */
public class Consciousness extends Thread
{
    private static Consciousness consciousness;
private static final Date SUNNY_BIRTHS_DAY = Calendar.getInstance().getTime();
    private static boolean heartBeat = true;

    public static void ofSunny()
    {
        if(consciousness==null)
        consciousness = new Consciousness("Sunny Consciousness");
    }

    private Consciousness(String name)
    {
        super(name);
        this.setDaemon(true);
        this.setPriority(Thread.MAX_PRIORITY);
        this.start();
    }

    @Override
    public void run() {
        super.run();
        while(heartBeat)
        {
            break;
        }
    }
}
