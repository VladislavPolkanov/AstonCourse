import org.example.ArithmeticCalculator;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TestNGArithmeticCalculator {

    @Test
    public void testAdd() { assertEquals(ArithmeticCalculator.add(2, 3), 5); }
    @Test
    public void testSubtract() { assertEquals(ArithmeticCalculator.subtract(3, 2), 1); }
    @Test
    public void testMultiply() { assertEquals(ArithmeticCalculator.multiply(2, 3), 6); }
    @Test
    public void testDivide() { assertEquals(ArithmeticCalculator.divide(5, 2), 2.5); }

    @Test(expectedExceptions = ArithmeticException.class, expectedExceptionsMessageRegExp = "Деление на ноль недопустимо")
    public void testDivideByZero() { ArithmeticCalculator.divide(1, 0); }

}
