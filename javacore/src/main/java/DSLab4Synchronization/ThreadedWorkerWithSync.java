package DSLab4Synchronization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ThreadedWorkerWithSync extends Thread {
    private final ResourcesExploiter rExp;

    @Override
    public void run() {
        synchronized (rExp) {
            for (int i = 0; i < 1000; i++) {
                rExp.exploit();
            }
        }
    }
}