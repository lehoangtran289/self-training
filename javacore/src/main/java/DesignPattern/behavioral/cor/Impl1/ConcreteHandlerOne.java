package DesignPattern.behavioral.cor.Impl1;

public class ConcreteHandlerOne extends Handler {

    public ConcreteHandlerOne(Handler successor) {
        super(successor);
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getValue() < 0) {
            System.out.println("\tConcreteHandlerOne.HandleRequest: " + request.getValue());
        } else {
            super.handleRequest(request);
        }
    }
}
