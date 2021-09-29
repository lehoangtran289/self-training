package Algo.OTP;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class TOTP {
    private final String otpValue;
    private final long timestamp;
    private final int digits;
    private final long timeStep;
    private final HashAlgorithm hashAlgorithm;

    public TOTP(String otpValue, long timestamp, HashAlgorithm hashAlgorithm, int digits, long timeStep) {
        this.otpValue = otpValue;
        this.timestamp = timestamp;
        this.hashAlgorithm = hashAlgorithm;
        this.digits = digits;
        this.timeStep = timeStep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TOTP totp = (TOTP) o;
        return StringUtils.equals(otpValue, totp.otpValue);
    }

    @Override
    public int hashCode() {
        return otpValue.hashCode();
    }
}
