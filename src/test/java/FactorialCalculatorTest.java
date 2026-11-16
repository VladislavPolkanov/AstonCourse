import org.example.FactorialCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialCalculatorTest {
    @Test
    void testFactorialOfZero() { assertEquals(1, FactorialCalculator.calculate(0)); }
    @Test
    void testFactorialOfFive() { assertEquals(120, FactorialCalculator.calculate(5)); }
    @Test
    void testNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> FactorialCalculator.calculate(-1));
    }
}
