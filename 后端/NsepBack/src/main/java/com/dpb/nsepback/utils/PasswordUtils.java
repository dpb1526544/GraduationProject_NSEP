package com.dpb.nsepback.utils;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.BCrypt;

public class PasswordUtils {

    private static final String BCRYPT_PREFIX = "bcrypt$";
    private static final String SHA256_PREFIX = "sha256$";

    private PasswordUtils() {
    }

    public static String encode(String rawPassword) {
        if (rawPassword == null) {
            return null;
        }
        return BCRYPT_PREFIX + BCrypt.hashpw(rawPassword);
    }

    public static boolean isEncoded(String password) {
        return isBcryptEncoded(password) || isSha256Encoded(password);
    }

    public static boolean shouldUpgrade(String storedPassword) {
        return !isBcryptEncoded(storedPassword);
    }

    public static boolean isStrong(String rawPassword) {
        if (rawPassword == null || rawPassword.length() < 8) {
            return false;
        }
        boolean hasLetter = rawPassword.chars().anyMatch(Character::isLetter);
        boolean hasDigit = rawPassword.chars().anyMatch(Character::isDigit);
        return hasLetter && hasDigit;
    }

    public static boolean matches(String rawPassword, String storedPassword) {
        if (rawPassword == null || storedPassword == null) {
            return false;
        }
        if (isBcryptEncoded(storedPassword)) {
            return BCrypt.checkpw(rawPassword, storedPassword.substring(BCRYPT_PREFIX.length()));
        }
        if (isSha256Encoded(storedPassword)) {
            return (SHA256_PREFIX + DigestUtil.sha256Hex(rawPassword)).equals(storedPassword);
        }
        return rawPassword.equals(storedPassword);
    }

    private static boolean isBcryptEncoded(String password) {
        return password != null && password.startsWith(BCRYPT_PREFIX);
    }

    private static boolean isSha256Encoded(String password) {
        return password != null && password.startsWith(SHA256_PREFIX);
    }
}
