package DesignPattern.CoR.Impl3;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Dollar10Dispenser extends BaseHandler {

    @Override
    public void handle(Currency cur) {
        if (cur.getAmount() >= 10) {
            int num = cur.getAmount() / 10;
            int remainder = cur.getAmount() % 10;
            System.out.println("dispense " + num + " $10");
            if (remainder != 0)
                super.handle(new Currency(remainder));
        } else {
            super.handle(cur);
        }
    }
}
