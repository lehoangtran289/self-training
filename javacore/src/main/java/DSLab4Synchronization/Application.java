package DSLab4Synchronization;

public class Application {

    public static void testWorkerWithoutSync() {
        ResourcesExploiter resource = new ResourcesExploiter(0);
        ThreadedWorkerWithoutSync worker1 = new ThreadedWorkerWithoutSync(resource);
        ThreadedWorkerWithoutSync worker2 = new ThreadedWorkerWithoutSync(resource);
        ThreadedWorkerWithoutSync worker3 = new ThreadedWorkerWithoutSync(resource);

        worker1.start();
        worker2.start();
        worker3.start();
        try {
            worker1.join();
            worker2.join();
            worker3.join();
            System.out.println("Without sync, resource = " + resource.getRsc());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testWorkerWithSync() {
        ResourcesExploiter resource = new ResourcesExploiter(0);
        ThreadedWorkerWithSync worker1 = new ThreadedWorkerWithSync(resource);
        ThreadedWorkerWithSync worker2 = new ThreadedWorkerWithSync(resource);
        ThreadedWorkerWithSync worker3 = new ThreadedWorkerWithSync(resource);

        worker1.start();
        worker2.start();
        worker3.start();

        try {
            worker1.join();
            worker2.join();
            worker3.join();
            System.out.println("With sync, resource = " + resource.getRsc());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testWorkerWithLock() {
        ResourceExploiterWithLock resource = new ResourceExploiterWithLock(0);
        ThreadedWorkerWithLock worker1 = new ThreadedWorkerWithLock(resource);
        ThreadedWorkerWithLock worker2 = new ThreadedWorkerWithLock(resource);
        ThreadedWorkerWithLock worker3 = new ThreadedWorkerWithLock(resource);

        worker1.start();
        worker2.start();
        worker3.start();

        try {
            worker1.join();
            worker2.join();
            worker3.join();
            System.out.println("With lock, resource = " + resource.getRsc());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testWorkerWithoutSync();
        testWorkerWithSync();
        testWorkerWithLock();
    }
}
