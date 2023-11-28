package Java;

import org.junit.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.Assert.assertEquals;

public class RoundNumberTest {

    @Test
    public void testRoundNumber() {
        // Given Column (p, s) = (25, 5)
        int scale = 5;

        // Example 1
        BigDecimal original1 = new BigDecimal("12345.12345467");
        BigDecimal expected1 = new BigDecimal("12345.12345");
        BigDecimal rounded1 = roundNumber(original1, scale);
        assertEquals(expected1, rounded1);

        // Example 2
        BigDecimal original2 = new BigDecimal("12345.123456");
        BigDecimal expected2 = new BigDecimal("12345.12346");
        BigDecimal rounded2 = roundNumber(original2, scale);
        assertEquals(expected2, rounded2);
    }

    private BigDecimal roundNumber(BigDecimal number, int scale) {
        return number.setScale(scale, RoundingMode.HALF_UP);
    }
}

