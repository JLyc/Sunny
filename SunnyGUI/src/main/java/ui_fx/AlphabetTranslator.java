package ui_fx;

import javafx.application.Platform;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by JLyc on 5. 2. 2015.
 */
public class AlphabetTranslator implements Runnable {

    private static Map<String, Color> alphabet = loadAlphabet();
    private SunnyFace instanceOfSunny;
    private String sentence;
    private static final Random RND = new Random();

    public AlphabetTranslator(SunnyFace instanceOfSunny, String sentence) {
        this.instanceOfSunny = instanceOfSunny;
        this.sentence = sentence.toLowerCase();
    }

    private static Map<String, Color> loadAlphabet() {
        alphabet = new HashMap<>();

        alphabet.put("a", new Color(0, 0, 180));
        alphabet.put("b", new Color(175, 13, 102));
        alphabet.put("c", new Color(146, 248, 70));
        alphabet.put("d", new Color(255, 200, 47));
        alphabet.put("e", new Color(255, 118, 0));
        alphabet.put("f", new Color(185, 185, 185));
        alphabet.put("g", new Color(235, 235, 222));
        alphabet.put("h", new Color(100, 100, 100));
        alphabet.put("i", new Color(255, 255, 0));
        alphabet.put("j", new Color(55, 19, 112));
        alphabet.put("k", new Color(255, 255, 150));
        alphabet.put("l", new Color(202, 62, 94));
        alphabet.put("m", new Color(205, 145, 63));
        alphabet.put("n", new Color(12, 75, 100));
        alphabet.put("o", new Color(255, 0, 0));
        alphabet.put("p", new Color(175, 155, 50));
        alphabet.put("q", new Color(0, 0, 0));
        alphabet.put("r", new Color(37, 70, 25));
        alphabet.put("s", new Color(121, 33, 135));
        alphabet.put("t", new Color(83, 140, 208));
        alphabet.put("u", new Color(0, 154, 37));
        alphabet.put("v", new Color(178, 220, 205));
        alphabet.put("w", new Color(255, 152, 213));
        alphabet.put("x", new Color(0, 0, 74));
        alphabet.put("y", new Color(175, 200, 74));
        alphabet.put("z", new Color(63, 25, 12));
        return alphabet;
    }

    @Override
    public void run() {
        try {
            for(int index=0;index < sentence.length(); index++) {
            Color colorCode = alphabet.get(String.valueOf(sentence.charAt(index)));

            if(colorCode==null){
                colorCode = new Color(RND.nextInt(255), RND.nextInt(255), RND.nextInt(255));
            }

            final Color finalColorCode = colorCode;
            Platform.runLater(() -> instanceOfSunny.pb[0].setProgress((double) finalColorCode.getBlue() / 255+0.2d));
            Platform.runLater(() -> instanceOfSunny.pb[1].setProgress((double)finalColorCode.getRed() / 255+0.2d));
            Platform.runLater(() -> instanceOfSunny.pb[2].setProgress((double)finalColorCode.getGreen() / 255+0.2d));

                Thread.sleep(100);
                Platform.runLater(() -> instanceOfSunny.pb[0].setProgress(0.4d));
                Platform.runLater(() -> instanceOfSunny.pb[1].setProgress(0.5d));
                Platform.runLater(() -> instanceOfSunny.pb[2].setProgress(0.4d));
                Thread.sleep(50);
            }

            Platform.runLater(() -> instanceOfSunny.pb[0].setProgress(-1.0d));
            Platform.runLater(() -> instanceOfSunny.pb[1].setProgress(-1.0d));
            Platform.runLater(() -> instanceOfSunny.pb[2].setProgress(-1.0d));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
