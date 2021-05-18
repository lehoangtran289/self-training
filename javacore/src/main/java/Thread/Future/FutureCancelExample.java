package Thread.Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        long s = System.currentTimeMillis();
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "Hello world";
        });

        while (!future.isDone()) {
            System.out.println("Task is still not done...");
            Thread.sleep(200);
            if (System.currentTimeMillis() - s > 1000) {
                future.cancel(true);
            }
        }

        System.out.println("Task completed! Retrieving the result");
        System.out.println(future.isDone() + " " + future.isCancelled());
        if(!future.isCancelled()) {
            System.out.println("Task completed! Retrieving the result");
            System.out.println(future.get());
            System.out.println("here");
        } else {
            System.out.println("Task was cancelled");
        }
        executorService.shutdown();
    }
}
