package com.dpb.nsepback.utils;

import cn.hutool.crypto.digest.DigestUtil;

public class PasswordUtils {

    private static final String PREFIX = "sha256$";

    private PasswordUtils() {
    }

    public static String encode(String rawPassword) {
        if (rawPassword == null) {
            return null;
        }
        return PREFIX + DigestUtil.sha256Hex(rawPassword);
    }

    public static boolean isEncoded(String password) {
        return password != null && password.startsWith(PREFIX);
    }

    public static String ensureEncoded(String password) {
        if (password == null) {
            return null;
        }
        return isEncoded(password) ? password : encode(password);
    }

    public static boolean matches(String rawPassword, String storedPassword) {
        if (rawPassword == null || storedPassword == null) {
            return false;
        }
        if (isEncoded(storedPassword)) {
            return encode(rawPassword).equals(storedPassword);
        }
        return rawPassword.equals(storedPassword);
    }
}

