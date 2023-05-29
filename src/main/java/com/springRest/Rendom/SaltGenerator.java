package com.springRest.Rendom;

import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

public class SaltGenerator {

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.encodeBase64String(salt);
    }
}
