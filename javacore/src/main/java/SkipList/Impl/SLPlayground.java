package SkipList.Impl;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class SLPlayground {
    public static void main(String[] args) {
        int iterations = (int) Math.pow(10, 6);
        int fail = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            try {
                SkipList<Integer, String> sl = new SkipList<>();
                for (int j = 0; j < iterations; j++) {
                    sl.insert(j, "Test" + j);
                }
                sl.delete(5);
                sl.delete(4);
//                sl.printSkipList();
//                System.out.println("search for key=" + 178232 + " -> " + sl.search(178232));
            } catch (Exception e) {
                e.printStackTrace();
                fail++;
            }
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            try {
                 Map<Integer, String> map = new ConcurrentSkipListMap<>();
                for (int j = 0; j < iterations; j++) {
                    map.put(j, "Test" + j);
                }
                map.remove(5);
                map.remove(4);
            } catch (Exception e) {
                e.printStackTrace();
                fail++;
            }
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            try {
                 Map<Integer, String> map = new TreeMap<>();
                for (int j = 0; j < iterations; j++) {
                    map.put(j, "Test" + j);
                }
                map.remove(5);
                map.remove(4);
            } catch (Exception e) {
                e.printStackTrace();
                fail++;
            }
        }
        System.out.println(System.currentTimeMillis() - start);

    }
}
