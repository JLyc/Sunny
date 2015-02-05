package progres_bar_thread_study;

/**
 * Created by JLyc on 5. 2. 2015.
 */
public class MainClass {
    static CountThread countThread1 = new CountThread();
    static CountThread countThread2 = new CountThread();

    public static void main(String[] args) {
        new Thread(countThread1).start();
        new Thread(countThread2).start();
        JavaFXExecutorService.main(args);
    }

    public static CountThread getCountThread1() {
        return countThread1;
    }

    public static CountThread getCountThread2() {
        return countThread2;
    }
}
