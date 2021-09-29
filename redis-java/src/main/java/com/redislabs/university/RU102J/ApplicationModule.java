package com.redislabs.university.RU102J;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.Configuration;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ApplicationModule extends AbstractModule {

    private final JedisPool jedisPool;

    public ApplicationModule(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Provides
    public RediSolarConfiguration configuration(Configuration configuration)
    {
        return (RediSolarConfiguration) configuration;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Integer> lst = new LinkedList<>();
        for(int i = 0; i < 10000000; ++i) {
            lst.add(1);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("LinkedList: " + end + "ms");

        start = System.currentTimeMillis();
        List<Integer> lst2 = new ArrayList<>();
        for(int i = 0; i < 10000000; ++i) {
            lst2.add(1);
        }
        end = System.currentTimeMillis() - start;
        System.out.println("ArrayList: " + end + "ms");
    }

    @Provides
    public JedisPool provideJedisPool() {
        return jedisPool;
    }
}
