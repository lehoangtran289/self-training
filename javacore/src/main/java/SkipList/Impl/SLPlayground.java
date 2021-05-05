package SkipList.Impl;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class SLPlayground {
    private static final int iterations = 200; // (int) Math.pow(10, 6);

    public static void implSkipListTest() {
        long start = System.currentTimeMillis();
        SkipList<Integer, String> sl = new SkipList<>();
        for (int j = 0; j < iterations; j++) {
            sl.insert(j, "Test" + j);
        }
        sl.delete(5);
        sl.delete(4);
        sl.printSkipList();
        System.out.println("search for key=" + 178 + " -> " + sl.search(178));
//        System.out.println(System.currentTimeMillis() - start);
    }

    public static void concurrentSkipListTest() {
        long start = System.currentTimeMillis();
        Map<Integer, String> map = new ConcurrentSkipListMap<>();
        for (int j = 0; j < iterations; j++) {
            map.put(j, "Test" + j);
        }
        map.remove(5);
        map.remove(4);
//        System.out.println(System.currentTimeMillis() - start);
    }

    public static void main(String[] args) {
        implSkipListTest();
        concurrentSkipListTest();
    }
}
