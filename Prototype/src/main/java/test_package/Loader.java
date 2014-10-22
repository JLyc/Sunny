package test_package;

/**
 * Created by socha on 22.10.2014.
 */
class Loader {
    static final String theName = "The Loader";
    static {
        System.out.println("Loader.static");
    }
    Loader() {
        System.out.println("Loader.Loader()");
    }
}

//class Test2 {
//    static {
//        System.out.println( "Test.static");
//    }
//    Test2() {
//        System.out.println( "Test.Test()");
//    }
//    public static void main( String [] args ) {
//        System.out.println( "Test.main");
//        Test t = new Test();
//        System.exit(0);
//    }
//}
