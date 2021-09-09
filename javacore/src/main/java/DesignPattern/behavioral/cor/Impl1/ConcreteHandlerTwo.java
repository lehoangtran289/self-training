package DesignPattern.behavioral.cor.Impl1;

public class ConcreteHandlerTwo extends Handler {

    public ConcreteHandlerTwo(Handler successor) {
        super(successor);
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getValue() > 0) {
            System.out.println("\tConcreteHandlerTwo.HandleRequest: " + request.getValue());
        } else {
            super.handleRequest(request);
        }
    }
}
