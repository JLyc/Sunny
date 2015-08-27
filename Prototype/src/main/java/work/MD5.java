/*
 * Copyright (c) 2015. All rights reserved for autor and owner of email domain JLyc.Development@gmail.com.
 */

package work;

import java.math.*;
import java.security.*;

/**
 * Created 20. 8. 2015.
 *
 * @author JLyc
 */
public class MD5 {


    public static void main(String[] args){

        MessageDigest md = null;
        String[] inputs = {
                "1440059489537:1f757bb012c7e28112edafda43db9733b47c9342",
        "1440059982089:1f757bb012c7e28112edafda43db9733b47c9342",
        "1440059993404:1f757bb012c7e28112edafda43db9733b47c9342",
        "1440060004438:1f757bb012c7e28112edafda43db9733b47c9342",
        "1440060005827:1f757bb012c7e28112edafda43db9733b47c9342",
        "1440060007183:1f757bb012c7e28112edafda43db9733b47c9342",
        "1440060322239:1f757bb012c7e28112edafda43db9733b47c9342"
        };
        for( int index= 0 ; index < inputs.length;index++){
            alternative(inputs[index]);
        try {
            md = MessageDigest.getInstance("MD5");
            BigInteger bi = new BigInteger(md.digest(inputs[index].getBytes()));
            System.out.println(bi.toString(16)+ " --------- lenght: " + bi.toString(16).length());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }}


    }

    public static void alternative(String inputs) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inputs.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            System.out.println(sb.toString()+ " --------- lenght: " + sb.toString().length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

/*String hash;
    try
    {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(Input.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

      hash = sb.toString();
      Output = hash;
    }
    catch (NoSuchAlgorithmException  ex) {
        Output= "Could not generate hash from String";
        }





}
}
*/
