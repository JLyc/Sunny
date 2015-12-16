/*
 * Copyright (c) 2015. All rights reserved for autor and owner of email domain JLyc.Development@gmail.com.
 */

package test_package;
import java.text.Normalizer;
import java.util.regex.Pattern;
/**
 * Created 27. 8. 2015.
 *
 * @author JLyc
 */
public class latin {

    public static void main(String[] args) {

        latin latin = new latin();
        System.out.println(latin.deAccent("�����辝�������"));
//        Transliterator.getInstance("NFD; [:Nonspacing Mark:] Remove; NFC");
    }

    public String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
