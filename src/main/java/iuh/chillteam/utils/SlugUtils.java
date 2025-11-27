package iuh.chillteam.utils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Slug utility class
 * Convert tiếng Việt có dấu sang slug URL-friendly
 */
public class SlugUtils {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern MULTIPLE_DASHES = Pattern.compile("-{2,}");

    /**
     * Convert text sang slug
     *
     * Example:
     * "Ray-Ban Aviator Classic" -> "ray-ban-aviator-classic"
     * "Kính mát thời trang" -> "kinh-mat-thoi-trang"
     */
    public static String toSlug(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        String slug = input.toLowerCase(Locale.ROOT);

        // Convert Vietnamese characters
        slug = convertVietnamese(slug);

        // Normalize unicode
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);

        // Replace whitespace with dash
        slug = WHITESPACE.matcher(slug).replaceAll("-");

        // Remove non-latin characters (except dash and underscore)
        slug = NON_LATIN.matcher(slug).replaceAll("");

        // Replace multiple dashes with single dash
        slug = MULTIPLE_DASHES.matcher(slug).replaceAll("-");

        // Remove leading and trailing dashes
        slug = slug.replaceAll("^-+|-+$", "");

        return slug;
    }

    /**
     * Convert Vietnamese characters to non-accent
     */
    private static String convertVietnamese(String str) {
        str = str.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
        str = str.replaceAll("[èéẹẻẽêềếệểễ]", "e");
        str = str.replaceAll("[ìíịỉĩ]", "i");
        str = str.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
        str = str.replaceAll("[ùúụủũưừứựửữ]", "u");
        str = str.replaceAll("[ỳýỵỷỹ]", "y");
        str = str.replaceAll("đ", "d");
        return str;
    }

    /**
     * Generate unique slug with suffix
     * Example: "ray-ban-aviator" -> "ray-ban-aviator-1"
     */
    public static String generateUniqueSlug(String baseSlug, int suffix) {
        return suffix == 0 ? baseSlug : baseSlug + "-" + suffix;
    }
}