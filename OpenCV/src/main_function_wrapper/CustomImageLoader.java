/*
 * Copyright (c) 2015. All rights reserved for autor and owner of email domain JLyc.Development@gmail.com.
 */

package main_function_wrapper;

import org.opencv.core.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

/**
 * Created 28. 8. 2015.
 *
 * @author JLyc
 */
public class CustomImageLoader {

    public static Mat createMat(BufferedImage image, int cvType){
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), cvType);
        mat.put(0, 0, data);
        return mat;
    }

    public static Mat emptyMatFrom(BufferedImage image, int cvType){
        Mat mat = new Mat(image.getHeight(),image.getWidth(),cvType);
        return mat;
    }

    public static RenderedImage renderedImage(Mat mat) {
        byte[] data = new byte[mat.rows() * mat.cols() * (int) (mat.elemSize())];
        mat.get(0, 0, data);
        BufferedImage renderedImage = new BufferedImage(mat.cols(),mat.rows(), BufferedImage.TYPE_3BYTE_BGR);
        renderedImage.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
        return renderedImage;
    }


    public static RenderedImage renderedImage(Mat mat, int imageType) {
        byte[] data = new byte[mat.rows() * mat.cols() * (int) (mat.elemSize())];
        mat.get(0, 0, data);
        BufferedImage renderedImage = new BufferedImage(mat.cols(), mat.rows(), imageType);
        renderedImage.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
        return renderedImage;
    }

    public static BufferedImage loadImage(String imagePath){
        try {
            File input = new File(imagePath);
            BufferedImage loadedImage = ImageIO.read(input);
            return convertImageToOpenCVcompatible(loadedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveImage(RenderedImage image, String imagePath){
        try {
            File output = new File(imagePath);
            ImageIO.write(image, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage convertImageToOpenCVcompatible(BufferedImage source){
        ColorConvertOp cco = new ColorConvertOp(null);

        BufferedImage destination = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        destination = cco.filter(source, destination);
        return destination;
    }

    public static BufferedImage captureScreen(){
        Robot robot = null;
        try {
            robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            return convertImageToOpenCVcompatible(screenShot);
        } catch (AWTException e) {
            e.printStackTrace();
            return null;
        }

    }

}
