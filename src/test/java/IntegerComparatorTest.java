import org.example.IntegerComparator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerComparatorTest {

    @Test
    void testFirstIsGreater() { assertEquals("Первое число больше второго", IntegerComparator.compare(10, 5)); }
    @Test
    void testFirstIsLess() { assertEquals("Первое число меньше второго", IntegerComparator.compare(3, 7)); }
    @Test
    void testAreEqual() { assertEquals("Числа равны", IntegerComparator.compare(4, 4)); }

}
