package test_package;

/**
 * Created by socha on 22.10.2014.
 */
public class Test1 {
    static {
        System.out.println( "Test.static");
    }
    Test1() {
        System.out.println( "Test.Test()");
        // Define a loader and print out the static name
        Loader l;
        System.out.println( Loader.theName );
    }
    public static void main( String [] args ) {
        System.out.println( "Test.main");
        Test t = new Test();
        System.exit(0);
    }
}
