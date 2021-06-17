import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicJunitTest {

    @BeforeEach
    public void initBeforeEach() {
        System.out.println("In beforeEach() method");
    }

    @TestFactory
    public Collection<DynamicTest> dynamicTestsWithCollection() {
        System.out.println("---exec dynamicTestsWithCollection");
        return Arrays.asList(
                dynamicTest("Add test",
                        () -> {
                            System.out.println("add test");
                            assertEquals(2, Math.addExact(1, 1));
                        }),
                dynamicTest("Multiply Test",
                        () -> {
                            System.out.println("multiply test");
                            assertEquals(4, Math.multiplyExact(2, 2));
                        }));
    }

    @TestFactory
    public Iterable<DynamicTest> dynamicTestsWithIterable() {
        System.out.println("---exec dynamicTestsWithIterable");
        return Arrays.asList(
                dynamicTest("Add test",
                        () -> assertEquals(2, Math.addExact(1, 1))),
                dynamicTest("Multiply Test",
                        () -> assertEquals(4, Math.multiplyExact(2, 2))));
    }

    @TestFactory
    public Iterator<DynamicTest> dynamicTestsWithIterator() {
        System.out.println("---exec dynamicTestsWithIterator");
        return Arrays.asList(
                dynamicTest("Add test",
                        () -> assertEquals(2, Math.addExact(1, 1))),
                dynamicTest("Multiply Test",
                        () -> assertEquals(4, Math.multiplyExact(2, 2))))
                .iterator();
    }

    @TestFactory
    public Stream<DynamicTest> dynamicTestsFromIntStream() {
        System.out.println("---exec dynamicTestsFromIntStream");
        return IntStream.iterate(0, n -> n + 2).limit(10)
                .mapToObj(n -> dynamicTest("test" + n,
                        () -> assertEquals(0, n % 2)));
    }

    @TestFactory
    public Stream<DynamicTest> testDifferentMultiplyOperations() {
        NumberClass numberClass = new NumberClass();
        int[][] data = new int[][]{{1, 2, 2}, {2, 8, 16}, {120, 4, 480}};
        return Arrays.stream(data).map(item -> {
            int m1 = item[0];
            int m2 = item[1];
            int expected = item[2];
            return dynamicTest(m1 + " * " + m2 + " = " + expected, () -> {
                assertEquals(expected, numberClass.multiply(m1, m2));
            });
        });
    }

    private static class NumberClass {
        public int multiply(int i, int j) {
            return i * j;
        }
    }
}
