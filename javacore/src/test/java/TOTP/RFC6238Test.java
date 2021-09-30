package TOTP;

import Algo.TOTP.HashAlgorithm;
import com.google.common.collect.ImmutableSet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static Algo.TOTP.CommonUtils.hexStrToBytes;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RFC6238Test {
    private static final long[] TEST_TIME = {59L, 1111111109L, 1111111111L, 1234567890L, 2000000000L, 20000000000L};
    public static final byte[] KEY1 = hexStrToBytes("3132333435363738393031323334353637383930");
    public static final byte[] KEY256 = hexStrToBytes("3132333435363738393031323334353637383930313233343536373839303132");
    public static final byte[] KEY512 = hexStrToBytes("31323334353637383930313233343536373839303132333435363738393031323334353637383930313233343536373839303132333435363738393031323334");
    public static final Set<TestVector> TEST_VECTORS = ImmutableSet.<TestVector>builder()
            .add(new TestVector("94287082", TEST_TIME[0], HashAlgorithm.HMAC_SHA_1, KEY1))
            .add(new TestVector("46119246", TEST_TIME[0], HashAlgorithm.HMAC_SHA_256, KEY256))
            .add(new TestVector("90693936", TEST_TIME[0], HashAlgorithm.HMAC_SHA_512, KEY512))
            .add(new TestVector("07081804", TEST_TIME[1], HashAlgorithm.HMAC_SHA_1, KEY1))
            .add(new TestVector("68084774", TEST_TIME[1], HashAlgorithm.HMAC_SHA_256, KEY256))
            .add(new TestVector("25091201", TEST_TIME[1], HashAlgorithm.HMAC_SHA_512, KEY512))
            .add(new TestVector("14050471", TEST_TIME[2], HashAlgorithm.HMAC_SHA_1, KEY1))
            .add(new TestVector("67062674", TEST_TIME[2], HashAlgorithm.HMAC_SHA_256, KEY256))
            .add(new TestVector("99943326", TEST_TIME[2], HashAlgorithm.HMAC_SHA_512, KEY512))
            .add(new TestVector("89005924", TEST_TIME[3], HashAlgorithm.HMAC_SHA_1, KEY1))
            .add(new TestVector("91819424", TEST_TIME[3], HashAlgorithm.HMAC_SHA_256, KEY256))
            .add(new TestVector("93441116", TEST_TIME[3], HashAlgorithm.HMAC_SHA_512, KEY512))
            .add(new TestVector("69279037", TEST_TIME[4], HashAlgorithm.HMAC_SHA_1, KEY1))
            .add(new TestVector("90698825", TEST_TIME[4], HashAlgorithm.HMAC_SHA_256, KEY256))
            .add(new TestVector("38618901", TEST_TIME[4], HashAlgorithm.HMAC_SHA_512, KEY512))
            .add(new TestVector("65353130", TEST_TIME[5], HashAlgorithm.HMAC_SHA_1, KEY1))
            .add(new TestVector("77737706", TEST_TIME[5], HashAlgorithm.HMAC_SHA_256, KEY256))
            .add(new TestVector("47863826", TEST_TIME[5], HashAlgorithm.HMAC_SHA_512, KEY512)).build();

    @Getter
    public static final class TestVector {
        private final String totp;
        private final long testTime;
        private final HashAlgorithm algorithm;
        private final int digits = 8;
        private final byte[] key;
        private final long timeStepSize = TimeUnit.SECONDS.toMillis(30);

        private TestVector(String totp, long testTime, HashAlgorithm algorithm, byte[] key) {
            this.totp = totp;
            this.testTime = TimeUnit.SECONDS.toMillis(testTime);
            this.algorithm = algorithm;
            this.key = new byte[key.length];
            System.arraycopy(key, 0, this.key, 0, key.length);
        }
    }
}
