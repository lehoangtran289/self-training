package DesignPattern.behavioral.cor.Impl2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Dollar50Dispenser implements DispenseChain{
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 50) {
            int num = cur.getAmount() / 50;
            int remainder = cur.getAmount() % 50;
            System.out.println("dispense " + num + " $50");
            if (remainder != 0)
                this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(cur);
        }
    }
}
