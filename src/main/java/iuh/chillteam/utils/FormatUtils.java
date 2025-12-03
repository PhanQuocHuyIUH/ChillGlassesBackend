package iuh.chillteam.utils;

/**
 * Format utility class
 */
public class FormatUtils {

    /**
     * Format price VND
     * Example: 1500000 -> "1.500.000đ"
     */
    public static String formatPrice(double price) {
        return String.format("%,.0f", price).replace(",", ".") + "đ";
    }

    /**
     * Format discount percent
     * Example: 0.15 -> "15%"
     */
    public static String formatPercent(double percent) {
        return String.format("%.0f%%", percent * 100);
    }

    /**
     * Format currency (Double) VND
     * Example: 1500000.0 -> "1.500.000đ"
     */
    public static String formatCurrency(Double amount) {
        if (amount == null) {
            return "0đ";
        }
        return formatPrice(amount);
    }
}