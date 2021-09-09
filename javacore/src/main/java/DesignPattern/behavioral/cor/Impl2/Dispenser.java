package DesignPattern.behavioral.cor.Impl2;

public abstract class Dispenser implements DispenseChain{
    private DispenseChain successor;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.successor = nextChain;
    }

    @Override
    public void dispense(Currency cur) {

    }
}
