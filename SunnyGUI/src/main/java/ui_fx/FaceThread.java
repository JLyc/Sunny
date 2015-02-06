package ui_fx;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by JLyc on 21. 1. 2015.
 *
 */
public class FaceThread extends Thread {
    private static FaceThread instance;

    private FaceThread(){}

    @Override
    public void run() {
        super.run();
        SunnyFace.show();
    }

    public static FaceThread getInstance() {
        if (instance == null) instance = new FaceThread();
        return instance;
    }

    public void buildFace(){
        instance.start();
    }

    public static SunnyFace sf;

    public static void returnInstance(SunnyFace sunnyFace) {
        sf = sunnyFace;
    }

    private static Executor executor = Executors.newSingleThreadExecutor();
    private static Thread visualiseThread;

    public static void endVisualiseSay(){

        if(visualiseThread.isAlive())
        {
            visualiseThread.interrupt();
        }
    }

    public static void visualiseSay(String text) {
        while(sf == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        executor.execute(new AlphabetTranslator(sf,text));
        visualiseThread = new Thread(new AlphabetTranslator(sf, text));
        visualiseThread.start();
    }
}
