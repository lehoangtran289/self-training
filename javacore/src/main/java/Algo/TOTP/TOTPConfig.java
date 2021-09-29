package Algo.TOTP;

import lombok.experimental.UtilityClass;

import java.util.concurrent.TimeUnit;

@UtilityClass
public class TOTPConfig {
    public static final long DEFAULT_TIME_STEP = TimeUnit.SECONDS.toMillis(30);
    public static final int DEFAULT_DIGITS = 6;
    public static final long T0 = 0;
    public static final HashAlgorithm DEFAULT_HASH_ALGORITHM = HashAlgorithm.HMAC_SHA_512;

    public static final int DEFAULT_SYNC_STEPS = 1;
}
