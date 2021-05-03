package SkipList;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class SLPlayground {
    public static void main(String[] args) {
        int fail = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            try {
                SkipList<Integer, String> sl = new SkipList<Integer, String>();
                for (int j = 0; j < Math.pow(10, 6); j++) {
                    sl.insert(j, "Test" + j);
                }
                sl.delete(5);
                sl.delete(4);
//                sl.printSkipList();

//                System.out.println("search for key=" + 178232 + " -> " + sl.search(178232));
//                System.out.println("search for key=" + 1001 + " -> " + sl.search(1001));
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
                for (int j = 0; j < Math.pow(10, 6); j++) {
                    map.put(j, "Test" + j);
                }
                map.remove(5);
                map.remove(4);

//                System.out.println("search for key=" + 178232 + " -> " + map.get(178232));
//                System.out.println("search for key=" + 1001 + " -> " + map.get(1001));
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
                for (int j = 0; j < Math.pow(10, 6); j++) {
                    map.put(j, "Test" + j);
                }
                map.remove(5);
                map.remove(4);

//                System.out.println("search for key=" + 178232 + " -> " + map.get(178232));
//                System.out.println("search for key=" + 1001 + " -> " + map.get(1001));
            } catch (Exception e) {
                e.printStackTrace();
                fail++;
            }
        }
        System.out.println(System.currentTimeMillis() - start);

    }
}
