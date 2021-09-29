package Algo.OTP;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

import java.util.concurrent.TimeUnit;

@UtilityClass
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class TOTPConfig {
    long DEFAULT_TIME_STEP = TimeUnit.SECONDS.toMillis(30);
    int DIGITS = 6;
    long T0 = 0; // UNIX time epoch
    HashAlgorithm HASH_ALGORITHM = HashAlgorithm.HMAC_SHA_512;
}
