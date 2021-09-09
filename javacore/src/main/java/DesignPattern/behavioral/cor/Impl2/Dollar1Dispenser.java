package DesignPattern.behavioral.cor.Impl2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Dollar1Dispenser implements DispenseChain{
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 1) {
            int num = cur.getAmount();
            System.out.println("dispense " + num + " $1");
        } else {
            this.chain.dispense(cur);
        }
    }
}
