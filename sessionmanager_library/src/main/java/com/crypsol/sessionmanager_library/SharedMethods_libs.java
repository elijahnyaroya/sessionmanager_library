package com.crypsol.sessionmanager_library;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SharedMethods_libs {

    public static String generateMyImeiNo() {
        String myImeiCandidate = md5("" + new Random().nextInt(1000000000) + 1000000000 + ";" + System.currentTimeMillis());
        return myImeiCandidate;
    }

    private static String md5(String s) {
        try {

            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
