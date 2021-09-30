package Thread.AtomicVariables;

public class SafetyCounterWithLock {
    private volatile int counter;

    public synchronized void increment() {
        counter++;
    }
}
