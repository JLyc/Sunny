package test_package;

/**
 * Created by sochaa on 16. 12. 2014.
 */
public class nieco {

    int a;
    String b;

    public nieco(int a) {
        this.a = a;
    }

    public nieco(String b) {
        this.b = b;
    }

    public static void main(String[] args) {
        Object my = "String";
        (String) my;
        new nieco(my);
    }
}
