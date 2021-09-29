package Algo.random;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomWithProbability {
    private static final LoadingCache<Object, Integer> bucketCache;
    private static final int proportion = 100;
    private static final int cacheExpireTime = 86400;

    static {
        bucketCache = CacheBuilder.newBuilder()
                                  .expireAfterAccess(cacheExpireTime, TimeUnit.SECONDS)
                                  .build(new CacheLoader<>() {
                                      @Override
                                      public Integer load(Object key) {
                                          return 0;
                                      }
                                  });
    }

    public <T> T random(List<T> objects, List<Double> distributions) {
        if (objects.size() != distributions.size())
            throw new IllegalArgumentException("Invalid input, object list size != distribution list size");

        List<T> toBeRandomized = new ArrayList<>();
        for (int i = 0; i < objects.size(); ++i) {
            T object = objects.get(i);
            Integer bucketSize = bucketCache.getUnchecked(object);
            if (bucketSize < (int) (distributions.get(i) * proportion))
                toBeRandomized.add(object);
        }
        if (CollectionUtils.isEmpty(toBeRandomized)) {  // reset if all bucket is full
            for (T object : objects) {
                bucketCache.put(object, 0);
                toBeRandomized.add(object);
            }
        }
        // process
        int index = 0;
        double rdNumber = Math.random();
        double ratio = 1 / distributions.stream().mapToDouble(Double::doubleValue).sum();
        double accumulatedDist = 0;
        for (int i = 0; i < toBeRandomized.size(); ++i) {
            accumulatedDist += distributions.get(i);
            if (rdNumber / ratio <= accumulatedDist) {
                index = i;
                break;
            }
        }
        T result = toBeRandomized.get(index);
        bucketCache.put(result, bucketCache.getUnchecked(result) + 1);
        return result;
    }
}
