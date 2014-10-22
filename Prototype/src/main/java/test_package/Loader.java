package test_package;

/**
 * Created by socha on 22.10.2014.
 */
class Loader {
    static String theName = "The Loader";

    static {
        System.out.println("Loader.static");
    }

    Loader() {
        System.out.println("Loader.Loader()");
    }
}
