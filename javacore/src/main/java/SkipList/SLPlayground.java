package SkipList;

public class SLPlayground {
    public static void main(String[] args) {
        int fail = 0;
        for (int i = 0; i < 1; i++) {
            try {
                SkipList<Integer, String> sl = new SkipList<Integer, String>();
                for (int j = 0; j < 1000; j++) {
                    sl.insert(j, "Test" + j);
                }
                sl.delete(5);
                sl.delete(4);
                sl.printSkipList();

                System.out.println("search for key=" + 178 + " -> " + sl.search(178));
            } catch (Exception e) {
                e.printStackTrace();
                fail++;
            }
        }
    }
}
