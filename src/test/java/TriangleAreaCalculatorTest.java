import org.example.TriangleAreaCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleAreaCalculatorTest {

    @Test
    void testAreaCalculation() { assertEquals(10.0, TriangleAreaCalculator.calculate(4, 5)); }
    @Test
    void testInvalidDimensions() {
        assertThrows(IllegalArgumentException.class, () -> TriangleAreaCalculator.calculate(-1, 5));
    }

}
