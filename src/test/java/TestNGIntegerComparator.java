import org.example.IntegerComparator;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TestNGIntegerComparator {

    @Test
    public void testFirstIsGreater() { assertEquals(IntegerComparator.compare(10, 5), "Первое число больше второго"); }
    @Test
    public void testFirstIsLess() { assertEquals(IntegerComparator.compare(3, 7), "Первое число меньше второго"); }
    @Test
    public void testAreEqual() { assertEquals(IntegerComparator.compare(4, 4), "Числа равны"); }

}
