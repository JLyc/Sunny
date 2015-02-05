package progres_bar_thread_study;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class CountThread implements Runnable {

    int num_of_count = 5;
    CountDownLatch counter;
    String name;

    DoubleProperty processProperty;

    CountThread() {
        processProperty = new SimpleDoubleProperty(0);
    }

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            double num = 1 - ((float) i)/100;
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            processProperty.setValue(1);
//            processProperty.set(ProgressIndicator.INDETERMINATE_PROGRESS);
            System.out.println(num);
        }
    }

    private static CountThread countThread;

    public static CountThread getInstance() {
        if (countThread == null) countThread = new CountThread();
        return countThread;
    }
}

