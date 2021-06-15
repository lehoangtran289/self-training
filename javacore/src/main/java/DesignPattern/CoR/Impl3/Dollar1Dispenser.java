package DesignPattern.CoR.Impl3;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Dollar1Dispenser extends BaseHandler {

    @Override
    public void handle(DesignPattern.CoR.Impl3.Currency cur) {
        if (cur.getAmount() >= 1) {
            int num = cur.getAmount();
            System.out.println("dispense " + num + " $10");
        } else {
            super.handle(cur);
        }
    }
}