package DSLab4Synchronization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ThreadedWorkerWithLock extends Thread {
    private ResourceExploiterWithLock rExp;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            rExp.exploit();
        }
    }
}
