package StreamAPI;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();

        // Adding items to the pQueue using add()
        pQueue.add(10);
        pQueue.add(20);
        pQueue.add(15);
        pQueue.add(12);
        pQueue.add(13);
        pQueue.add(8);

        System.out.println(pQueue);

        // Printing the top element of PriorityQueue
        System.out.println(pQueue.peek());

        // Printing the top element and removing it
        // from the PriorityQueue container
        System.out.println(pQueue.poll());

        // Printing the top element again
//        System.out.println(pQueue.peek());

        for (Integer integer : pQueue) {
            System.out.println(integer);
        }
    }
}
