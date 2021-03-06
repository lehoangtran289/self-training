package SkipList.benchmark;

import SkipList.Impl.SkipList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5)
public class CollectionsBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        private final SkipList<Integer, Employee> sl = new SkipList<>();
        private final Map<Integer, Employee> m = new ConcurrentSkipListMap<>();
        private final List<Integer> arr = new ArrayList<>();
        private final List<Integer> ll = new LinkedList<>();

        private final long iterations = 1000;

        @Setup(Level.Trial)
        public void setUp() {
            for (long i = 0; i < iterations; i++) {
                sl.insert((int) i, new Employee(i, "Test"));
                m.put((int) i, new Employee(i, "Test"));
            }
        }
    }

    @Benchmark
    public Employee testSL(MyState state) {
        return state.sl.search(178887);
    }

    @Benchmark
    public Employee testCSL(MyState state) {
        return state.m.get(178887);
    }

    @Benchmark
    public long testLL(MyState state) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; ++i) {
            state.ll.add(1);
        }
        return System.currentTimeMillis() - start;
    }

    @Benchmark
    public long testAL(MyState state) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; ++i) {
            state.arr.add(1);
        }
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(CollectionsBenchmark.class.getSimpleName())
                .forks(1).build();
        new Runner(options).run();
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Employee {
        private long id;
        private String name;
    }
}

