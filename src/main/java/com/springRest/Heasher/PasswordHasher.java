package com.springRest.Heasher;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {

    public static String hashPassword(String password, String salt) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(salt + password);
    }

    public static boolean verifyPassword(String password, String salt, String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(salt + password, hashedPassword);
    }
}
