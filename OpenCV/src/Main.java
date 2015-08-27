import org.opencv.core.*;
import org.opencv.imgproc.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Main {
    public static void main( String[] args ) {

        try {
            Robot robot = new Robot();

            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(screenShot, "JPG", new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\backround.jpg"));
            File input = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\backround.jpg");

            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
//            File input = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
            BufferedImage image = ImageIO.read(input);
            image = (BufferedImage) screenShot;
//            ImageIO.read()

            DataBufferByte dataBufferByte = screenShot.getRaster().getDataBuffer().getData();
            image.getRaster().getDataBuffer().getData
            byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
            mat.put(0, 0, data);

            Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
            Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

            byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
            mat1.get(0, 0, data1);
            BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
            image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

            File ouptut = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert1.jpg");
            ImageIO.write(image1, "jpg", ouptut);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}