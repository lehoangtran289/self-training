package Algo.TOTP;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {

    /**
     * This method converts HEX string to Byte[]
     *
     * @param hex the HEX string
     * @return A byte array
     */
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

    public static String bytesToHex(byte[] buf) {
        StringBuilder strBuf = new StringBuilder(buf.length * 2);
        for (byte b : buf) {
            if (((int) b & 0xff) < 0x10)
                strBuf.append('0');
            strBuf.append(Long.toString((int) b & 0xff, 16));
        }
        return strBuf.toString().toUpperCase();
    }
}
