package DesignPattern.behavioral.cor.Impl1;

public class Example {

    public static void main(String[] args) {
        // SETUP COR
        Handler h1 = new ConcreteHandlerOne(new ConcreteHandlerTwo(new ConcreteHandlerThree()));

        // SEND REQUEST
        h1.handleRequest(new Request(-1));
        h1.handleRequest(new Request(0));
        h1.handleRequest(new Request(1));
        h1.handleRequest(new Request(2));
        h1.handleRequest(new Request(-5));
    }
}
