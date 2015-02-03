package ui_fx;

import java.util.concurrent.TimeUnit;

/**
 * Created by JLyc on 3. 2. 2015.
 */
public class TestMove {
    public static void main(String[] args) throws InterruptedException {
        SunnyFace sf = SunnyFace.show();

        Thread th = new Thread();


        int index =0;
        while(index < 100)
        {
            TimeUnit.SECONDS.sleep(2);

            sf.pb[1].setProgress(index/100);
            System.out.println("progres set");
            index++;
        }
    }
}
