package Thread.AtomicVariables;

import java.util.concurrent.Future;

public class SafetyCounterWithLock {
    private volatile int counter;

    public synchronized void increment() {
        counter++;
    }
}
