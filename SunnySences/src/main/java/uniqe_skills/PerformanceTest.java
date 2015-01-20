package uniqe_skills;

import neural_center.initialization.BasicKnowledge;

/**
 * Created by sochaa on 8. 12. 2014.
 */
public class PerformanceTest {
    private static long startTime;


    public static void totalcount()
    {

    }

    public static void start() {
        startTime = System.currentTimeMillis();
    }

    public static void result()
    {
        long finishTime = System.currentTimeMillis();
        System.out.println("That took: " + (finishTime - startTime) + " ms");
    }

    public static void main(String[] args) {
        BasicKnowledge.getInstance();

    }
}
