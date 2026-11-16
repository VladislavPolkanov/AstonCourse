import org.example.ArithmeticCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticCalculatorTest {
    @Test
    void testAdd() { assertEquals(5, ArithmeticCalculator.add(2, 3)); }
    @Test
    void testSubtract() { assertEquals(1, ArithmeticCalculator.subtract(3, 2)); }
    @Test
    void testMultiply() { assertEquals(6, ArithmeticCalculator.multiply(2, 3)); }
    @Test
    void testDivide() { assertEquals(2.5, ArithmeticCalculator.divide(5, 2)); }
    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> ArithmeticCalculator.divide(1, 0));
        assertEquals("Деление на ноль недопустимо", exception.getMessage());
    }
}
