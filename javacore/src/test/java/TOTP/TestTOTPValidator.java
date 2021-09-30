package TOTP;

import Algo.TOTP.CommonUtils;
import Algo.TOTP.TOTPValidator;
import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static Algo.TOTP.CommonUtils.hexStrToBytes;
import static org.junit.Assert.assertTrue;

public class TestTOTPValidator {

    @Test
    public void RFC6238Test() {
        for (RFC6238Test.TestVector testVector : RFC6238Test.TEST_VECTORS) {
            long validateTime = testVector.getTestTime() + TimeUnit.SECONDS.toMillis(30); // drift 1 step
            assertTrue(TOTPValidator.builder().syncSteps(1).build().isOTPValid(
                    testVector.getKey(), testVector.getTimeStepSize(), testVector.getDigits(),
                            testVector.getAlgorithm(), null, testVector.getTotp(), validateTime));
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(
                Arrays.toString("12345678901234567890".getBytes()),
                Arrays.toString(hexStrToBytes("3132333435363738393031323334353637383930"))
        );

        byte[] hmacResult = hexStrToBytes("1f8698690e02ca16618550ef7f19da8e945b555a");
        int offset = hmacResult[hmacResult.length - 1] & 0xf;   // 4 LSB
        int binCode = ((hmacResult[offset] & 0x7f) << 24) |
                ((hmacResult[offset + 1] & 0xff) << 16) |
                ((hmacResult[offset + 2] & 0xff) << 8) |
                (hmacResult[offset + 3] & 0xff);

        // Generate TOTP
        int otp = binCode % ((int) Math.pow(10, 6));
        Assertions.assertEquals("872921", StringUtils.leftPad(Integer.toString(otp), 6, '0'));
    }

    @Test
    public void test2() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2040, 12, 12), LocalTime.of(12, 12));
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

        long T = Math.floorDiv(zdt.toInstant().toEpochMilli(), TimeUnit.SECONDS.toMillis(30));
        System.out.println(T);

        String timeInHex = StringUtils.leftPad(Long.toHexString(T).toUpperCase(), 16, '0');
        System.out.println(timeInHex);
        System.out.println(Arrays.toString(hexStrToBytes(timeInHex)));

//        System.out.println(timeAndTransIdInHex);
//        String timeAndTransIdInHex = combineFactors(Long.toHexString(T).toUpperCase(), strToHexStr("transactionId")).substring(0, 16);
//        System.out.println(Arrays.toString(hexStrToBytes(timeAndTransIdInHex)));
    }

}
