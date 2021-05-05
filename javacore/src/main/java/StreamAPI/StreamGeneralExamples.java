package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamGeneralExamples {
    public static void isStreamLazyTest() {
        System.out.println("\nisStreamLazyTest");

        List<Integer> intLst = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> intStream = intLst.stream()
                .filter(i -> {
                    System.out.println("filter called, i = " + i);
                    return i % 2 == 0;
                })
                .map(i -> {
                    System.out.println("map called, i = " + i);
                    return ++i;
                });
        System.out.println("Before terminal op -> " + intLst);

        intLst = intStream.collect(Collectors.toList());
        System.out.println("After terminal op -> " + intLst);
    }

    public static void onceOffStreamTest() {
        System.out.println("\nonceOffStreamTest");

        List<Integer> intLst = Arrays.asList(1, 2, 3, 4, 5, 6);
        Stream<Integer> intStream = intLst.stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i * 2);
        System.out.println("Before terminal op -> " + intLst);

        intLst = intStream.collect(Collectors.toList());
        System.out.println("After terminal op -> " + intLst);

        long count = intStream.count();
        System.out.println("here" + count);
    }

    public static void onceOffStreamTest2() {
        System.out.println("\nonceOffStreamTest2");

        List<Integer> intLst = Arrays.asList(1, 2, 3, 4, 5, 6);
        Stream<Integer> intStream = intLst.stream();
        Stream<Integer> s1 = intStream.filter(i -> i % 2 == 0);
        Stream<Integer> s2 = intStream.map(i -> i * 2);
        System.out.println("Before terminal op -> " + intLst);

        intLst = intStream.collect(Collectors.toList());
        System.out.println("After terminal op -> " + intLst);

        System.out.println(s1.count() + " " + s2.count());
    }

    public static void infiniteStreamCreation() {
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
        oddNumbers.forEach(i -> {
            System.out.println(i);
            if (i > 100000) {
                System.exit(0);
            }
        });
    }

    public static void streamTest() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        Stream<String> c1 = s.map(str -> "x");
        Stream<String> c2 = s.filter(str -> str.contains("a"));
        System.out.println(c2.collect(Collectors.toList()));
    }

    private static int divide(int value, int factor) {
        int result = 0;
        try {
            result = value / factor;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception: Division by Zero");
        }
        return result;
    }

    public static int divideListElements(List<Integer> values, int divider) {
        return values.stream().reduce(0, (a, b) -> divide(a, divider) + divide(b, divider));
    }

    public static void handleExceptionTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    public static void main(String[] args) {
//        isStreamLazyTest();
//        onceOffStreamTest();
//        onceOffStreamTest2();
//        infiniteStreamCreation();

        streamTest();
    }
}
