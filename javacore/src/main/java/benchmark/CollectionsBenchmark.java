package benchmark;

import SkipList.SkipList;
import lombok.AllArgsConstructor;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5)
public class CollectionsBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        private Set<Employee> employeeSet = new HashSet<>();
        private List<Employee> employeeList = new ArrayList<>();

        private SkipList<Integer, Employee> sl = new SkipList<Integer, Employee>();
        private Map<Integer, Employee> m = new ConcurrentSkipListMap<>();

        private long iterations = 1000000;

        private Employee employee = new Employee(100L, "Harry");

        @Setup(Level.Trial)
        public void setUp() {
//            for (long i = 0; i < iterations; i++) {
//                employeeSet.add(new Employee(i, "John"));
//                employeeList.add(new Employee(i, "John"));
//            }
//
//            employeeList.add(employee);
//            employeeSet.add(employee);

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

//    @Benchmark
//    public boolean testArrayList(MyState state) {
//        return state.employeeList.contains(state.employee);
//    }
//
//    @Benchmark
//    public boolean testHashSet(MyState state) {
//        return state.employeeSet.contains(state.employee);
//    }

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(CollectionsBenchmark.class.getSimpleName())
                .forks(1).build();
        new Runner(options).run();
    }

    @AllArgsConstructor
    public static class Employee {
        private Long id;
        private String name;

        // constructor and getter setters go here
    }
}

