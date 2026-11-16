import org.example.FactorialCalculator;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TestNGFactorialCalculator {

    @Test
    public void testFactorialOfZero() { assertEquals(FactorialCalculator.calculate(0), 1); }
    @Test
    public void testFactorialOfFive() { assertEquals(FactorialCalculator.calculate(5), 120); }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeNumber() { FactorialCalculator.calculate(-1); }

}
