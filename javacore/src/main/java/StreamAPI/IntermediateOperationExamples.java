package StreamAPI;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperationExamples {

    public static void flatMapTest() {
        List<String> zero = Collections.emptyList();
        List<String> one = Collections.singletonList("bonobo");
        List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(zero, one, two);

//        animals.forEach(System.out::println);

        animals.flatMap(Collection::stream)
                .forEach(System.out::println);
    }

    public static void streamPeekTest() {
        Stream<Integer> infinitee = Stream.iterate(1, x -> x + 1);
        infinitee.limit(5)
                .peek(System.out::print)
                .filter(x -> x % 2 == 1)
                .forEach(System.out::print); //11233455
    }

    public static void main(String[] args) {
//        streamPeekTest();
        flatMapTest();
    }
}
