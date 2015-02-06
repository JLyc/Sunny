package test_package;

import java.io.InputStream;

/**
 * Created by JLyc on 6. 2. 2015.
 */
public class ResourcesLoad {
    public static void main(String[] args) {
        InputStream isLoaded = ResourcesLoad.class.getClassLoader().getResourceAsStream("Sunny.png");
        System.out.println(isLoaded);
        InputStream isLoaded1 = ResourcesLoad.class.getClassLoader().getResourceAsStream("C:\\Users\\sochaa\\Pictures\\Sunny.png");
        System.out.println(isLoaded1);
    }
}
