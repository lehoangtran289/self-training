package DesignPattern.CoR.Impl1;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConcreteHandlerThree extends Handler {

    public ConcreteHandlerThree(Handler successor) {
        super(successor);
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getValue() == 0) {
            System.out.println("\tConcreteHandlerThree.HandleRequest: " + request.getValue());
        } else {
            super.handleRequest(request);
        }
    }
}
