package Algo.TOTP;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static Algo.TOTP.CommonUtils.hexStrToBytes;
import static Algo.TOTP.CommonUtils.strToHexStr;

public class TOTPGenerator {
    private final byte[] key;
    private long timeStepSize = TOTPConfig.DEFAULT_TIME_STEP;
    private int digits = TOTPConfig.DEFAULT_DIGITS;
    private HashAlgorithm hashAlgorithm = TOTPConfig.DEFAULT_HASH_ALGORITHM;

    public TOTPGenerator(byte[] key) {
        this.key = new byte[key.length];
        System.arraycopy(key, 0, this.key, 0, key.length);
    }

    public TOTPGenerator timeStepSize(long timeStepSize) {
        this.timeStepSize = timeStepSize;
        return this;
    }

    public TOTPGenerator digits(int digits) {
        this.digits = digits;
        return this;
    }

    public TOTPGenerator hashAlgo(HashAlgorithm hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
        return this;
    }

    public String generateTOTP(long timestamp, String transactionId) {
        // Step 0: Generate 8-byte counter C
        final long T = (timestamp - TOTPConfig.T0) / timeStepSize;
        final String counter = transactionId == null || transactionId.length() == 0 ?
                StringUtils.leftPad(Long.toHexString(T).toUpperCase(), 16, '0') :
                combineFactors(Long.toHexString(T).toUpperCase(), strToHexStr(transactionId)).substring(0, 16);

        // Step 1: Generate HMAC-SHA hash
        byte[] msg = hexStrToBytes(counter);
        byte[] hmacResult = computeHMACSHA(key, msg);

        // Step 2: Dynamic truncation
        int offset = hmacResult[hmacResult.length - 1] & 0xf;   // 4 LSB
        int binCode = ((hmacResult[offset] & 0x7f) << 24) |
                ((hmacResult[offset + 1] & 0xff) << 16) |
                ((hmacResult[offset + 2] & 0xff) << 8) |
                (hmacResult[offset + 3] & 0xff);

        // Step 3: Generate TOTP
        int otp = binCode % ((int) Math.pow(10, digits));
        return StringUtils.leftPad(Integer.toString(otp), digits, '0');
    }

    private byte[] computeHMACSHA(byte[] secretKey, byte[] message) {
        try {
            Mac hmac = Mac.getInstance(hashAlgorithm.getValue());
            SecretKeySpec macKey = new SecretKeySpec(secretKey, "RAW");
            hmac.init(macKey);
            return hmac.doFinal(message);
        } catch (GeneralSecurityException gse) {
            throw new UndeclaredThrowableException(gse);
        }
    }

    /**
     * insert `transIdInHex` into middle offset of `timeInHex` and hash using SHA-512
     */
    private static String combineFactors(String timeInHex, String transIdInHex) {
        String input = new StringBuilder(timeInHex).insert(timeInHex.length() / 2, transIdInHex).toString();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            return StringUtils.leftPad(no.toString(16), 32, '0');
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
