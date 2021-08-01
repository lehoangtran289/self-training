package DesignPattern.behavioral.cor.Impl3;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Dollar20Dispenser extends BaseHandler {

    @Override
    public void handle(Currency cur) {
        if (cur.getAmount() >= 20) {
            int num = cur.getAmount() / 20;
            int remainder = cur.getAmount() % 20;
            System.out.println("dispense " + num + " $50");
            if (remainder != 0)
                super.handle(new Currency(remainder));
        } else {
            super.handle(cur);
        }
    }
}
