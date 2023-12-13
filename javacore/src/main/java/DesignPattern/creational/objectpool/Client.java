package DesignPattern.creational.objectpool;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Client {
    public static final int NUM_OF_CLIENT = 8;

    public static void main(String[] args) {
        TaxiPool taxiPool = new TaxiPool();
        for (int i = 1; i <= NUM_OF_CLIENT; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println("New client: " + Thread.currentThread().getName());
                    Taxi taxi = taxiPool.getTaxi();

                    TimeUnit.MILLISECONDS.sleep(randInt(1000, 1500));

                    taxiPool.release(taxi);
                    System.out.println("Served the client: " + Thread.currentThread().getName());
                } catch (InterruptedException | TaxiPool.TaxiNotFoundException e) {
                    System.out.println(">>>Rejected the client: " + Thread.currentThread().getName());
                }
            });
            thread.start();
        }
    }

    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
