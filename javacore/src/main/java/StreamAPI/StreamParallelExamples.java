package StreamAPI;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamParallelExamples {

    public static void SeqVsParallelStreamTest() {
        int n = (int) Math.pow(10, 10);

        long start = System.currentTimeMillis();
        long sumSeq = LongStream
                .rangeClosed(1, n)
                .reduce(0L, Long::sum);
        System.out.println("sum = " + sumSeq);
        System.out.println("Sequential stream time = " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        long sumParallel = LongStream
                .rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println("sum = " + sumParallel);
        System.out.println("Parallel stream time = " + (System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        SeqVsParallelStreamTest();
    }
}
