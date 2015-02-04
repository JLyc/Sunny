package ui_fx;

import java.util.concurrent.TimeUnit;

/**
 * Created by JLyc on 3. 2. 2015.
 */
public class TestMove implements Runnable{
    private static SunnyFace sf;

    public static void main(String[] args) throws InterruptedException {


        Thread th = new Thread(new TestMove());
        th.start();
        TimeUnit.SECONDS.sleep(10);
        int index =0;
        while(index < 100)
        {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(sf==null){
                System.out.println("null");
                continue;
            }
            sf.pb[1].setProgress(index/100);
            System.out.println("progres set");
            index++;
        }


    }

    @Override
    public void run() {
        sf = SunnyFace.show();
        System.out.println(sf);
    }

    public static void returnInstance(SunnyFace sunnyFace) {
        sf = sunnyFace;
    }
}
