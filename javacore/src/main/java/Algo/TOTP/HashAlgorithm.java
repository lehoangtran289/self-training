package Algo.TOTP;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@ToString
public enum HashAlgorithm {
    HMAC_SHA_1("HmacSHA1"),
    HMAC_SHA_256("HmacSHA256"),
    HMAC_SHA_512("HmacSHA512"),
    SHA_512("SHA-512");

    private final String value;

    private static final Map<String, HashAlgorithm> map;
    static {
        map = new HashMap<>();
        for (HashAlgorithm algo : values()) {
            map.put(algo.value, algo);
        }
    }

    public static HashAlgorithm from(String val) {
        HashAlgorithm ret = map.get(val);
        if (ret == null) throw new IllegalStateException();
        return ret;
    }
}
