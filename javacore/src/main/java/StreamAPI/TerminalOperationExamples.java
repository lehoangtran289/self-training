package StreamAPI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TerminalOperationExamples {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class User {
        private String name;
        private int age;
    }

    public static void streamMaxMinTest() {
        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s.min((s1, s2) -> s2.length() - s1.length());
        System.out.print("min in stream is ");
        min.ifPresent(System.out::println);

        s = Stream.empty();
        Optional<String> max = s.max((s1, s2) -> s2.length() - s1.length());
        System.out.print("is max present? " + max.isPresent());
    }

    public static void streamFindTest() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Stream<String> sEmpty = Stream.empty();
        s.findAny().ifPresent(System.out::println); // monkey
        infinite.findAny().ifPresent(System.out::println); // chimp
        System.out.println(sEmpty.findAny().isPresent()); //false
    }

    public static void streamIterateTest() {
        // Java 8
        System.out.println("Stream iterate w/o predicate");
        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(10)
                .mapToInt(arr -> arr[0])
                .forEach(i -> System.out.print(i + " "));

        // >Java9
        System.out.println("\nStream iterate w/ predicate");
        Stream.iterate(new int[]{0, 1}, arr -> arr[0] < 50, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .mapToInt(arr -> arr[0])
                .forEach(i -> System.out.print(i + " "));
    }

    public static void streamMatchTest() {
        List<String> list = Arrays.asList("monkey", "2", "chimp");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));

        System.out.println(list.stream().anyMatch(pred)); // true
        System.out.println(list.stream().allMatch(pred)); // false
        System.out.println(list.stream().noneMatch(pred)); // false
        System.out.println(infinite.anyMatch(pred)); // true

        // infinite stream not terminated
        infinite = Stream.generate(() -> "2chimp");
        pred = x -> Character.isLetter(x.charAt(0));
        System.out.println(infinite.anyMatch(pred));
        System.out.println("here");
    }

    public static void streamReduceTest() {
        // 1st signature
        System.out.println("-----T reduce(T identity, BinaryOperator<T> accumulator) ");
        String[] array = new String[]{"w", "o", "l", "f"};
        String res = "";
        for (String s : array) res = res + s;
        System.out.println(res);

        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("", (s, c) -> s + c);
        System.out.println(word);

        // 2nd signature
        System.out.println("-----Optional<T> reduce(BinaryOperator<T> accumulator)");
        BinaryOperator<Integer> op = (a, b) -> a * b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> threeElements = Stream.of(3, 5, 6);

        empty.reduce(op).ifPresent(System.out::println); // no output
        oneElement.reduce(op).ifPresent(System.out::println); // 3
        threeElements.reduce(op).ifPresent(System.out::println); // 90

        // 3rd signature
        System.out.println("-----<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator " +
                "BinaryOperator<U> combiner)");
        List<User> users = Arrays.asList(new User("A", 30), new User("B", 35));

        int totalAge = users.stream()
                .reduce(0,  // need a way to convert user to int
                        (partialAge, obj) -> partialAge + obj.getAge(),
                        Integer::sum
                );
        System.out.println(totalAge);

        // alternative
        int totalAge2 = users.stream()
                .mapToInt(User::getAge)
                .sum();
        System.out.println(totalAge2);

        // parallel stream
        List<Integer> lst = Arrays.asList(10, 20, 30, 40, 50, 60);
        BinaryOperator<Integer> opr = Integer::sum;

        int lstParallelSum = lst.parallelStream().reduce(0, opr, opr);
        System.out.println("Sum by parallel = " + lstParallelSum);
        int lstSeqSum = lst.stream().reduce(0, opr, opr);
        System.out.println("Sum by sequence = " + lstSeqSum);
    }

    public static void streamForEach() {
        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .mapToInt(arr -> arr[0])
                .forEach(i -> {
                    if (i > 50) System.exit(0);
                    System.out.print(i + " ");
                });
    }

    public static void streamCollectTest() {
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        StringBuilder word = stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println(word);

        Stream<String> s = Stream.of("brown bear-", "grizzly-");
        s.sorted(Comparator.reverseOrder())
                .forEach(System.out::print); // grizzly-brown bear-
    }

    public static void main(String[] args) {
//        streamMaxMinTest();
//        streamFindTest();
//        streamIterateTest();
//        streamMatchTest();
//        streamReduceTest();
        streamCollectTest();
    }
}
