package iuh.chillteam.utils;

/**
 * String utility class
 */
public class StringUtils {

    /**
     * Check if string is null or empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Check if string is not empty
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Truncate string to max length
     */
    public static String truncate(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength) + "...";
    }

    /**
     * Capitalize first letter
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Generate random string
     */
    public static String randomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Generate random numeric string (OTP)
     */
    public static String randomNumeric(int length) {
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Mask email for privacy
     * Example: "user@example.com" -> "u***@example.com"
     */
    public static String maskEmail(String email) {
        if (isEmpty(email) || !email.contains("@")) {
            return email;
        }

        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];

        if (username.length() <= 1) {
            return email;
        }

        String masked = username.charAt(0) + "***";
        return masked + "@" + domain;
    }

    /**
     * Mask phone number
     * Example: "0123456789" -> "012****789"
     */
    public static String maskPhone(String phone) {
        if (isEmpty(phone) || phone.length() < 6) {
            return phone;
        }

        String prefix = phone.substring(0, 3);
        String suffix = phone.substring(phone.length() - 3);

        return prefix + "****" + suffix;
    }
}