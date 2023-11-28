package Java;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberRounding {

    public static void main(String[] args) {
        // Given Column (p, s) = (25, 5)
        int precision = 25;
        int scale = 5;

        // Example 1
        BigDecimal original1 = new BigDecimal("12345.12345467");
        BigDecimal expected1 = new BigDecimal("12345.12345");
        BigDecimal rounded1 = roundNumber(original1, scale);

        System.out.println("Original: " + original1);
        System.out.println("Expected: " + expected1);
        System.out.println("Rounded: " + rounded1);
        System.out.println("Result: " + (rounded1.compareTo(expected1) == 0 ? "Match" : "Mismatch"));
        System.out.println();

        // Example 2
        BigDecimal original2 = new BigDecimal("12345.123456");
        BigDecimal expected2 = new BigDecimal("12345.12346");
        BigDecimal rounded2 = roundNumber(original2, scale);

        System.out.println("Original: " + original2);
        System.out.println("Expected: " + expected2);
        System.out.println("Rounded: " + rounded2);
        System.out.println("Result: " + (rounded2.compareTo(expected2) == 0 ? "Match" : "Mismatch"));
    }

    private static BigDecimal roundNumber(BigDecimal number, int scale) {
        return number.setScale(scale, RoundingMode.HALF_UP);
    }
}
