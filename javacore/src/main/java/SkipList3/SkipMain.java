package SkipList3;

import SkipList4.SkipList;

import java.io.IOException;

public class SkipMain {
    public static void main(String args[]) throws IOException {

        int fail = 0;
        for (int i = 0; i < 100; i++) {
            try {
                SkipList3<Integer, Integer> dum = new SkipList3<Integer, Integer>();
                dum.insert(10, 10);
//                dum.print();
                dum.insert(5, 5);
//                dum.print();
                dum.insert(20, 20);
                dum.insert(15, 15);
                dum.insert(14, 14);
//                dum.print();
//                System.out.println("Search for 10: " + dum.find(10));
//                System.out.println("Search for 15: " + dum.find(15));
//                System.out.println("Search for 20: " + dum.find(20));
//                System.out.println("Search for 25: " + dum.find(25));
            } catch (Exception e) {
                fail++;
            }
        }
        System.out.println(fail);
    }
}
