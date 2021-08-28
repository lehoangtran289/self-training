package com.redislabs.university.RU102J.dao;

import redis.clients.jedis.*;

import java.time.ZonedDateTime;
import java.util.Random;

public class RateLimiterSlidingDaoRedisImpl implements RateLimiter {

    private final JedisPool jedisPool;
    private final long windowSizeMS;
    private final long maxHits;

    public RateLimiterSlidingDaoRedisImpl(JedisPool pool, long windowSizeMS,
                                          long maxHits) {
        this.jedisPool = pool;
        this.windowSizeMS = windowSizeMS;
        this.maxHits = maxHits;
    }

    // Challenge #7
    @Override
    public void hit(String name) throws RateLimitExceededException {
        // START CHALLENGE #7
        try (Jedis jedis = jedisPool.getResource()) {
            long currentTimeMs = ZonedDateTime.now().toEpochSecond() * 1000;
            String key = getKey(name);
            Transaction t = jedis.multi();
            t.zadd(key, currentTimeMs, generateUniqueMember());
            t.zremrangeByScore(key, Long.MIN_VALUE, currentTimeMs - windowSizeMS);
            Response<Long> response = t.zcard(key);
            t.exec();
            if (response.get() > maxHits) {
                throw new RateLimitExceededException();
            }
        }
        // END CHALLENGE #7
    }

    private String generateUniqueMember() {
        return ZonedDateTime.now().toEpochSecond() * 1000 + ":" + new Random().nextLong();
    }

    private String getKey(String name) {
        return RedisSchema.getRateLimiterSlideWindowKey(windowSizeMS, name, maxHits);
    }
}
