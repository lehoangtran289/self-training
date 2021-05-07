package SkipList.ConcurrentSkipList;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ConcurrentSkipListTest {

    public static void slTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        EventWindowSort eventWindowSort = new EventWindowSort();
        int numberOfThreads = 2;

        Runnable producer = () -> IntStream
                .rangeClosed(0, 100)
                .forEach(index -> eventWindowSort.acceptEvent(
                        new Event(ZonedDateTime.now().minusSeconds(index), UUID.randomUUID().toString()))
                );

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(producer);
        }
        ConcurrentNavigableMap<ZonedDateTime, String> eventsFromLastMinute =
                eventWindowSort.getEventsFromLastMinute();

        long eventsOlderThanOneMinute = eventsFromLastMinute
                .entrySet()
                .stream()
                .filter(e -> e.getKey().isBefore(ZonedDateTime.now().minusMinutes(1)))
                .count();
        System.out.println(eventsOlderThanOneMinute == 0);
        long eventYoungerThanOneMinute = eventsFromLastMinute
                .entrySet()
                .stream()
                .filter(e -> e.getKey().isAfter(ZonedDateTime.now().minusMinutes(1)))
                .count();
        System.out.println(eventYoungerThanOneMinute > 0);

    }

    public static void main(String[] args) {
        new Thread(ConcurrentSkipListTest::slTest).start();
    }
}
