package TOTP;

import Algo.TOTP.TOTPValidator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestTOTPValidator {

    @Test
    public void isValidShouldSucceedUnderRFC6238TestCases() {
        for (RFC6238TestVectors.TestVector testVector : RFC6238TestVectors.TEST_VECTORS) {
            assertTrue(TOTPValidator.builder().syncSteps(0).build()
                    .isOTPValid(testVector.getKey(), testVector.getTimeStep(), testVector.getDigits(),
                            testVector.getAlgorithm(), null, testVector.getTotp(), testVector.getTestTime()));
        }
    }

}
