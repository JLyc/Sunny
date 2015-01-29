package test_package;

import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.Arrays;

import static javax.sound.sampled.AudioSystem.getMixer;
import static javax.sound.sampled.AudioSystem.getMixerInfo;

/**
 * Created by JLyc on 29. 1. 2015.
 */
public class VolumeMixer {

    public static void main(String[] args) throws LineUnavailableException {
        Mixer.Info[] info = getMixerInfo();
        Mixer mixer;
        for(Mixer.Info o : info) {
//            if (o.getName().startsWith("Jack Mic")) {

                mixer = getMixer(o);
//                Line.Info[] lines = mixer.getTargetLineInfo();
//                System.out.println(o.getName() + " : " + lines.length);
                ArrayList<Line.Info> lines = new ArrayList<Line.Info>(Arrays.asList(mixer.getTargetLineInfo()));
                for (Line.Info srcInfo: lines) {
                    Port.Info pi =(Port.Info) srcInfo;
//                    Port.Info prot = (Port.Info) l;
                    System.out.println(pi.getName());
                }
            }
//        }
    }

}
