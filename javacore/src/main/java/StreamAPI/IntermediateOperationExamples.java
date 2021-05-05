package StreamAPI;

import java.util.stream.Stream;

public class IntermediateOperationExamples {

    public static void streamPeekTest() {
        Stream<Integer> infinitee = Stream.iterate(1, x -> x + 1);
        infinitee.limit(5)
                .peek(System.out::print)
                .filter(x -> x % 2 == 1)
                .forEach(System.out::print); //11233455
    }

    public static void main(String[] args) {
        streamPeekTest();
    }
}
