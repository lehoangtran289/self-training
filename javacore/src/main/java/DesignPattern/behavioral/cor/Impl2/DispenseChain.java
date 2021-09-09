package DesignPattern.behavioral.cor.Impl2;

public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);

    void dispense(Currency cur);
}
