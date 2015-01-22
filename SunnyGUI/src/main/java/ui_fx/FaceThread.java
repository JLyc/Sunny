package ui_fx;

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
}
