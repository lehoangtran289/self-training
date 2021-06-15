package DesignPattern.CoR.Impl3;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Dollar50Dispenser extends BaseHandler {

    @Override
    public void handle(Currency cur) {
        if (cur.getAmount() >= 50) {
            int num = cur.getAmount() / 50;
            int remainder = cur.getAmount() % 50;
            System.out.println("dispense " + num + " $50");
            if (remainder != 0)
                super.handle(new Currency(remainder));
        } else {
            super.handle(cur);
        }
    }
}
