package test_package;

import neural_center.initialization.EnvironmentOfOS;

/**
 * Created by socha on 22.10.2014.
 */
public class Test1 {
    static {
        System.out.println( "Test.static");
    }
    Test1() {
        System.out.println( "Test.Test()");
        Loader l;
        EnvironmentOfOS ofOS;
        System.out.println( Loader.theName );
//        System.out.println( EnvironmentOfOS.theName );
        System.out.println( EnvironmentOfOS.loadStaticBlock );
    }
    public static void main( String [] args ) {
        System.out.println( "Test.main");
        Test1 t = new Test1();
        System.exit(0);
    }
}
