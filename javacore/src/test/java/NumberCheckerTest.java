import JunitModels.NumberChecker;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NumberCheckerTest {
    private final Integer inputNumber;
    private final Boolean expectedResult;
    private NumberChecker numberChecker;

    @Before
    public void init() {
        numberChecker = new NumberChecker();
    }

    // Each parameter should be placed as an argument here
    public NumberCheckerTest(Integer inputNumber, Boolean expectedResult) {
        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> primeNumbers() {
        return Arrays.asList(new Object[][] {
                {2, true},
                {6, false},
                {19, true},
                {22, false},
                {23, true},
        });
    }

    @Test
    public void testPrimeNumberChecker() {
        System.out.println("Parameterized Number is: " + inputNumber);
        Assertions.assertEquals(expectedResult, numberChecker.validatePrime(inputNumber));
    }
}
