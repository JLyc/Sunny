package ui_fx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressBar;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JLyc on 3. 2. 2015.
 */
public class TestMove implements Runnable{
    public static SunnyFace sf;

    public static SimpleDoubleProperty R;
    public static SimpleDoubleProperty G;
    public static SimpleDoubleProperty B;
    static Map<String, Color> alphabet = new HashMap<>();
    private static ProgressBar pb;

    public static void loadAlphabet(){
    alphabet.put("a", new Color(0, 0, 180));
    alphabet.put("b", new Color(175, 13, 102));
    alphabet.put("c", new Color(146,248,70));
    alphabet.put("d", new Color(255, 200, 47));
    alphabet.put("e", new Color(255,118,0));
    alphabet.put("f", new Color(185,185,185));
    alphabet.put("g", new Color(235,235,222));
    alphabet.put("h", new Color(100,100,100));
    alphabet.put("i", new Color(255,255,0));
    alphabet.put("j", new Color(55,19,112));
    alphabet.put("k", new Color(255,255,150));
    alphabet.put("l", new Color(202,62,94));
    alphabet.put("m", new Color(205,145,63));
    alphabet.put("n", new Color(12,75,100));
    alphabet.put("o", new Color(255,0,0));
    alphabet.put("p", new Color(175,155,50));
    alphabet.put("q", new Color(0,0,0));
    alphabet.put("r", new Color(37,70,25));
    alphabet.put("s", new Color(121,33,135));
    alphabet.put("t", new Color(83,140,208));
    alphabet.put("u", new Color(0,154,37));
    alphabet.put("v", new Color(178,220,205));
    alphabet.put("w", new Color(255,152,213));
    alphabet.put("x", new Color(0,0,74));
    alphabet.put("y", new Color(175,200,74));
    alphabet.put("z", new Color(63,25,12));
    }


    public static void showFace() throws InterruptedException {
//        String veta1 = "Skakal pes cez voves cez zelenou louku siel za nim myslivec pero na klobouku pezku nas codelas. Nevim sam hop a klakal dale";
//        String veta = "a b c d e f g h i j k l m n o p q r s t u v w x y z ";
        Thread th = new Thread(new TestMove());
        th.start();
    }

    @Override
    public void run() {
        SunnyFace.show();
    }

    public static void returnInstance(SunnyFace sunnyFace) {
        sf = sunnyFace;
        System.out.println("i have instance: " + sf);
    }

    public static void send(ProgressBar pb) {
        TestMove.pb = pb;
    }

    public static void visauliseSay(String text) {
        while(sf == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new Thread(new AlphabetTranslator(sf, text)).start();
    }
}
