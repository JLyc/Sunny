package hearth;

import neural_center.initialization.SunnyInitialization;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by sochaa on 3. 12. 2014.
 */
public class Consciousness implements Runnable {
    private static Thread consciousness;
    private static final Date SUNNY_BIRTHS_DAY = Calendar.getInstance().getTime();
    private static boolean heartBeat = true;

    public static void ofSunny()
    {
        if(consciousness==null)
            consciousness = new Thread(new Consciousness());
        consciousness.setName("Sunny Consciousness");
        consciousness.setPriority(Thread.MAX_PRIORITY);
        consciousness.start();
    }

    private Consciousness(){
    }

    public void run() {
        SunnyInitialization.initialize();

        while(heartBeat)
        {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sunny died :(");
    }

    public static void main(String args[]){
        Consciousness.ofSunny();
    }
}
