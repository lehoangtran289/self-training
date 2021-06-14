package Thread.Future;

import java.util.concurrent.*;

public class CompletableFutureExample {

    public static void blockForeverXD() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        String result = completableFuture.get();
        System.out.println("here");
    }

    public static void manuallyComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("Future's Result"); // Subsequent calls to completableFuture.complete()
        // will be ignored.
        String result = completableFuture.get();
        System.out.println("r: " + result);
    }

    /**
     * Run a task specified by a Runnable Object asynchronously.
     * useful for tasks that don’t return anything
     * return CompletableFuture<Void>
     */
    public static void runAsyncExample() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println(Thread.currentThread().getName() + " Result of runAsync");
        });

        future.get();
    }

    /**
     * Run a task specified by a Supplier object asynchronously
     * returns CompletableFuture<T> where T is the type of the value obtained by calling the given supplier
     */
    public static void supplyAsyncExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.print(Thread.currentThread().getName() + " ");
            return "Result of the asynchronous computation";
        });

        // Block and get the result of the Future
        String result = future.get();
        System.out.println(result);
    }

    public static void callbackExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello1";
        });
        System.out.println("before");
        System.out.println(cf1.get() + " World1");   // need to get() to further transform
        System.out.println("after");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello";
        })
                .thenApply(s -> s + "_World");
        System.out.println(cf.get());
        System.out.println("after");
    }

    /**
     * Note: by default, supplyAsync run task in ForkJoinPool threads, which are daemon threads
     * if specify executorService as 2nd arg -> non-demon thread
     */
    public static void thenApplyAsyncExample() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("Apply: " + Thread.currentThread().getName());
            System.out.println("is demon: " + Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Some Result";
        }, es).thenApply(result -> {
            System.out.println("Then: " + result + " in " + Thread.currentThread().getName());
            return "Processed Result";
        });
        es.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName());
//        blockForeverXD();
//        manuallyComplete();
//        runAsyncExample();
//        supplyAsyncExample();
//        callbackExample();
        thenApplyAsyncExample();
    }

}
