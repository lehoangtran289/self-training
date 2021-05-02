package SkipList;

public class SLPlayground {
    public static void main(String[] args) {
        int fail = 0;
        for (int i = 0; i < 1; i++) {
            try {
                SkipList<Integer, String> sl = new SkipList<Integer, String>();
                sl.insert(1, "One");
                sl.insert(4, "Four");
                sl.insert(10, "Ten");
                sl.insert(3, "Three");
                sl.insert(11, "Eleven");
                sl.insert(10, "Ten10");
                sl.printSkipList();

                sl.delete(5);
                sl.delete(4);
                sl.printSkipList();

                System.out.println(sl.search(10));
            } catch (Exception e) {
                e.printStackTrace();
                fail++;
            }
        }
    }
}
