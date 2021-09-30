package param;

import JunitModels.NumberChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberParameterizedTest {

    // can only pass one argument to the test method each time.
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // six numbers
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        assertTrue(NumberChecker.isOdd(number));
    }

//    @ParameterizedTest
//    @NullAndEmptySource // not work with primitive
    void test(int number) {
        assertTrue(NumberChecker.isOdd(number));
    }
}
