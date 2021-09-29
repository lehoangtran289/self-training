package Algo.TOTP;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {

    public static byte[] hexStrToBytes(String s) {
        int length = s.length();
        byte[] result = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            result[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return result;
    }

    public static String strToHexStr(String s) {
        return String.format("%040x", new BigInteger(1, s.getBytes()));
    }
}
