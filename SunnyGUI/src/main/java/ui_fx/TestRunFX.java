package ui_fx;

/**
 * Created by sochaa on 2. 12. 2014.
 */
public class TestRunFX extends Thread{

    public static void main(String[] args) {
        new MainFX();
        TestRunFX tr = new TestRunFX();
        tr.start();
        MainFX.makeItWork();

    }


    public void run()
    {
        while(true) {
            System.out.println("just play");
            MainFX.p1.setProgress(1.0);
            MainFX.p2.setProgress(-1.0);
            MainFX.p3.setProgress(1.0);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainFX.p1.setProgress(-1.0);
            MainFX.p2.setProgress(-1.0);
            MainFX.p3.setProgress(-1.0);
        }
    }
}
