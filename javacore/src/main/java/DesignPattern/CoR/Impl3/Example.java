package DesignPattern.CoR.Impl3;

public class Example {
    public static void main(String[] args) {
        Handler h1 = new Dollar50Dispenser();
        Handler h2 = new Dollar20Dispenser();
        Handler h3 = new Dollar10Dispenser();
        Handler h4 = new Dollar1Dispenser();
        h1.setNext(h2);
        h2.setNext(h3);
        h3.setNext(h4);

        h1.handle(new Currency(123));
    }
}
