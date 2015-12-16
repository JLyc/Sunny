/*
 * Copyright (c) 2015. All rights reserved for author and owner of email domain JLyc.Development@gmail.com.
 */

package test_package;

import java.math.*;
import java.security.*;

/**
 * Created 13. 10. 2015.
 *
 * @author JLyc
 */
public class MD5 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
    String heslo = "#hu5&$9dP7KxT!=";
        //b4v3n9 vbednarik
        String hash;

        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(heslo.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        hash = sb.toString();

        System.out.println(hash);


        //-43868403482213ab4da2c1b8fe4cd1585a606874
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            BigInteger bi = new BigInteger(md.digest(heslo.getBytes()));

            heslo = bi.toString(16);

        }
        catch (NoSuchAlgorithmException  ex) {

        }
//47b0ae713e16da475c3e8530c9acf993
        String original = heslo;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        System.out.println(original);
        System.out.println(sb.toString());
    }

}
