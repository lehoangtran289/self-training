package Algo.TOTP;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TOTPValidator {
    private int syncSteps = TOTPConfig.DEFAULT_SYNC_STEPS;

    public boolean isOTPValid(byte[] key, long timeStepSize, int digits, HashAlgorithm hashAlgorithm,
                              String transactionId, String otpValue) {
        return isOTPValid(key, timeStepSize, digits, hashAlgorithm, transactionId, otpValue, System.currentTimeMillis());
    }

    public boolean isOTPValid(byte[] key, long timeStepSize, int digits, HashAlgorithm hashAlgorithm,
                              String transactionId, String otpValue, long validateTime) {
        TOTPGenerator totpGenerator = new TOTPGenerator(key).timeStepSize(timeStepSize)
                .digits(digits).hashAlgo(hashAlgorithm);
        for (int i = -syncSteps; i <= syncSteps; ++i) {
            long timestamp = validateTime + (i * timeStepSize);
            String generatedOTP = totpGenerator.generateTOTP(timestamp, transactionId);
            if (StringUtils.equals(generatedOTP, otpValue)) {
                return true;
            }
        }
        return false;
    }
}
