package DesignPattern.creational.singleton;

public class SingletonDemo {

    public static void main(String[] args) {
        switch (3) {
            case 1: singletonEagerInit(); break;
            case 2: singletonNotThreadSafe(); break;
            case 3: singletonThreadsafe(); break;
        }
    }

    public static void singletonEagerInit() {
        SingletonEagerInit object1 = SingletonEagerInit.getInstance();
        SingletonEagerInit object2 = SingletonEagerInit.getInstance();
        System.out.println(object1 == object2);

        object1.setInfo("New Info");
        System.out.println(object2.getInfo());
    }

    public static void singletonNotThreadSafe() {
        Thread task1 = new Thread(() -> {
            SingletonLazyInit object1 = SingletonLazyInit.getInstance();
            System.out.println(Thread.currentThread().getName() + " " + object1.getInfo());
            object1.setInfo("New Info1");
            sleep(3000);
            System.out.println(Thread.currentThread().getName() + " " + object1.getInfo());
        });

        Thread task2 = new Thread(() -> {
            SingletonLazyInit object2 = SingletonLazyInit.getInstance();
            System.out.println(Thread.currentThread().getName() + " " + object2.getInfo());
            object2.setInfo("New Info2");
            sleep(3000);
            System.out.println(Thread.currentThread().getName() + " " + object2.getInfo());
        });

        task1.start();
        task2.start();
    }

    public static void singletonThreadsafe() {
        Thread task1 = new Thread(() -> {
            SingletonThreadSafe object1 = SingletonThreadSafe.getInstance();
            System.out.println(Thread.currentThread().getName() + " " + object1.getInfo());
            object1.setInfo("New Info1");
            sleep(3000);
            System.out.println(Thread.currentThread().getName() + " " + object1.getInfo());
        });

        Thread task2 = new Thread(() -> {
            SingletonThreadSafe object2 = SingletonThreadSafe.getInstance();
            System.out.println(Thread.currentThread().getName() + " " + object2.getInfo());
            object2.setInfo("New Info2");
            sleep(3000);
            System.out.println(Thread.currentThread().getName() + " " + object2.getInfo());
        });

        task1.start();
        task2.start();
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
