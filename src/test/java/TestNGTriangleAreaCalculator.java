import org.example.TriangleAreaCalculator;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TestNGTriangleAreaCalculator {

    @Test
    public void testAreaCalculation() { assertEquals(TriangleAreaCalculator.calculate(4, 5), 10.0); }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidDimensions() { TriangleAreaCalculator.calculate(-1, 5); }
}
