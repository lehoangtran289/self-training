package LambdaExpression;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FunctInterfaceAPIExample {
    private static List<Integer> list = Arrays.asList(1, 5, -3, 7, -19, 120);

    public static void main(String[] args) {
        switch (5) {
            case 1: consumerExample(); break;
            case 2: predicateExample(); break;
            case 3: functionExample(); break;
            case 4: supplierExample(); break;
            case 5: customFunctInterface(); break;
        }
    }

    public static void consumerExample() {
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

        System.out.println("Equivalent to-----------------------------");

        list.forEach(i -> System.out.println(i));
        list.forEach(System.out::println);
    }

    public static void predicateExample() {
        list.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer t) {
                return t < 0;
            }
        });
        list.forEach(System.out::println);

        System.out.println("Equivalent to-----------------------------");

        list.removeIf(t -> t > 1);
        list.forEach(System.out::println);
    }

    public static void functionExample() {
        list.stream().map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return String.valueOf(integer);
            }
        }).forEach(System.out::println);

        System.out.println("Equivalent to-----------------------------");

        list.stream().map(String::valueOf).forEach(System.out::println);
    }

    public static void supplierExample() {
        Random random = new Random();
        Stream<Integer> stream = Stream.generate(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return random.nextInt(10);
            }
        }).limit(5);
        stream.forEach(t -> System.out.print(t + " "));

        System.out.println("Equivalent to--------------------");

        stream = Stream.generate(() -> random.nextInt(10)).limit(5);
        stream.forEach(t -> System.out.print(t + " "));
    }

    /**
     * Difference between `anonymous interface` vs `lambda expression` lies in local fields
     */
    public static void customFunctInterface() {
        MyEventConsumer eventConsumer = new MyEventConsumer() {
            private int count = 0; // this :D
            @Override
            public void consume(Object event) {
                System.out.println(event.toString() + " consumed " + this.count++ + " times.");
            }
        };
        applyCustom(eventConsumer);
    }

    private static void applyCustom(MyEventConsumer consumer) {
        for (int i = 0; i < 10; i++) {
            consumer.consume(i);
        }
    }
}
