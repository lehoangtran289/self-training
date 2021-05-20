package Thread.ForkJoin;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

public class CustomAction extends RecursiveAction {
    private static final int THRESHOLD = 5;

    private List<Long> data;

    public CustomAction(List<Long> data) {
        this.data = data;
    }

    @Override
    protected void compute() {
        if (data.size() <= THRESHOLD) {
            long sum = processing();
            System.out.println("sum of " + data.toString() + " = " + sum);
        } else {
            CustomAction p1 = new CustomAction(data.subList(0, data.size() / 2));
            CustomAction p2 = new CustomAction(data.subList(data.size() / 2, data.size()));

            ForkJoinTask.invokeAll(p1, p2);

//            p1.fork(); // queue the first task
//            p2.compute(); // compute the second task
//            p1.join(); // wait for the first task result
        }
    }

    private long processing() {
        return data.stream().mapToLong(Long::longValue).sum();
    }

    public static void main(String[] args) {
        List<Long> data = LongStream
                .rangeClosed(1, 30)
                .boxed().collect(Collectors.toList());

        ForkJoinPool pool = new ForkJoinPool();
        CustomAction task = new CustomAction(data);
        pool.invoke(task);
    }
}
