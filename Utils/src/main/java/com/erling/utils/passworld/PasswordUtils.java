package com.erling.utils.passworld;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String EncodePassword(String password) {
        return encoder.encode(password);
    }
    public static boolean VerifyPassword(String password, String encodedPassword) {
        return encoder.matches(password, encodedPassword);
    }
}
