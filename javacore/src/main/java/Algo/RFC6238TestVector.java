package Algo;

import Algo.TOTP.HashAlgorithm;
import Algo.TOTP.TOTPGenerator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static Algo.TOTP.CommonUtils.hexStrToBytes;

public class RFC6238TestVector {

    public static void main(String[] args) {
        // RFC6238 Test
        // Seed for HMAC-SHA1 - 20 bytes
        String seed = "3132333435363738393031323334353637383930";
        // Seed for HMAC-SHA256 - 32 bytes
        String seed32 = "3132333435363738393031323334353637383930" + "313233343536373839303132";
        // Seed for HMAC-SHA512 - 64 bytes
        String seed64 = "3132333435363738393031323334353637383930" +
                "3132333435363738393031323334353637383930" +
                "3132333435363738393031323334353637383930" +
                "31323334";
        long T0 = 0;
        long X = 30;
        long[] testTime = {59L, 1111111109L, 1111111111L, 1234567890L, 2000000000L, 20000000000L};

        StringBuilder steps = new StringBuilder("0");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            System.out.println(
                    "+---------------+-----------------------+" +
                            "------------------+--------+--------+");
            System.out.println(
                    "|  Time(sec)    |   Time (UTC format)   " +
                            "| Value of T(Hex)  |  TOTP  | Mode   |");
            System.out.println(
                    "+---------------+-----------------------+" +
                            "------------------+--------+--------+");

            for (long time : testTime) {
                long T = (time - T0) / X;
                steps = new StringBuilder(Long.toHexString(T).toUpperCase());
                while (steps.length() < 16) steps.insert(0, "0");
                String fmtTime = String.format("%1$-11s", time);
                String utcTime = df.format(new Date(time * 1000));

                System.out.print("|  " + fmtTime + "  |  " + utcTime + "  | " + steps + " |");
                TOTPGenerator generator = new TOTPGenerator(hexStrToBytes(seed)).digits(8).hashAlgo(HashAlgorithm.HMAC_SHA_1).timeStepSize(X);
                System.out.println(generator.generateTOTP(time, null) + "| SHA1   |");

                System.out.print("|  " + fmtTime + "  |  " + utcTime + "  | " + steps + " |");
                generator = new TOTPGenerator(hexStrToBytes(seed32)).digits(8).hashAlgo(HashAlgorithm.HMAC_SHA_256).timeStepSize(X);
                System.out.println(generator.generateTOTP(time, null) + "| SHA256 |");

                System.out.print("|  " + fmtTime + "  |  " + utcTime + "  | " + steps + " |");
                generator = new TOTPGenerator(hexStrToBytes(seed64)).digits(8).hashAlgo(HashAlgorithm.HMAC_SHA_512).timeStepSize(X);
                System.out.println(generator.generateTOTP(time, null) + "| SHA512 |");

                System.out.println(
                        "+---------------+-----------------------+" +
                                "------------------+--------+--------+");
            }
        } catch (final Exception e) {
            System.out.println("Error : " + e);
        }
    }
}
