package Algo.OTP;

import com.google.common.base.Strings;
import com.google.common.io.BaseEncoding;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.UndeclaredThrowableException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.time.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class TOTPBuilder {
    private final byte[] key;
    private long timeStep = TOTPConfig.DEFAULT_TIME_STEP;
    private int digits = TOTPConfig.DIGITS;
    private HashAlgorithm hashAlgorithm = TOTPConfig.HASH_ALGORITHM;

    public TOTPBuilder(byte[] key) {
        this.key = new byte[key.length];
        System.arraycopy(key, 0, this.key, 0, key.length);
    }

    public TOTP build(long timestamp) {
        return new TOTP(generateTOTP(timestamp), timestamp, hashAlgorithm, digits, timeStep);
    }

    private String generateTOTP(long timestamp) {
        // Generate 8-byte counter C
        final long T = Math.floorDiv(timestamp - TOTPConfig.T0, timeStep);   // T0 = 0
        final String counter = Strings.padStart(Long.toHexString(T).toUpperCase(), 16, '0');

        // Generate HMAC-SHA hash
        byte[] msg = hexStrToBytes(counter);
        byte[] hash = computeHMACSHA(key, msg);

        // Dynamic truncation
        int offset = hash[hash.length - 1] & 0xf;   // 4 LSB
        int binary = ((hash[offset] & 0x7f) << 24) |
                ((hash[offset + 1] & 0xff) << 16) |
                ((hash[offset + 2] & 0xff) << 8) |
                (hash[offset + 3] & 0xff);

        // Generate TOTP
        int otp = binary % ((int) Math.pow(10, digits));
        return Strings.padStart(Integer.toString(otp), digits, '0');
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

    private byte[] hexStrToBytes(String s) {
        int length = s.length();
        byte[] result = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            result[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return result;
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2040, 12, 12), LocalTime.of(12, 12));
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

        //
        long T = Math.floorDiv(zdt.toInstant().toEpochMilli(), TimeUnit.SECONDS.toMillis(30));
        String timeInHex = Strings.padStart(Long.toHexString(T).toUpperCase(), 16, '0');

        System.out.println(T);
        System.out.println(timeInHex);
        System.out.println(Arrays.toString(BaseEncoding.base16().decode(timeInHex)));
        System.out.println(Arrays.toString(timeInHex.getBytes()));
//        System.out.println(Arrays.toString(hexStrToBytes(timeInHex)));

        System.out.println(timeInHex.getBytes(StandardCharsets.US_ASCII).length);
        System.out.println(Long.toHexString(T));
    }
}
