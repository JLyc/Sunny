package work.adapter;

/**
 * Created by JLyc on 1. 4. 2015.
 */
public class Retype {
    public static void main(String[] args) {
        Object i = 20;
        Object j = (Double) i;
        if(j instanceof Integer){
            System.out.println(true);
        }
        if(j instanceof Double){
            System.out.println(false);
        }
    }
}
