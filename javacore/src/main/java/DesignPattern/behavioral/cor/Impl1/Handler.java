package DesignPattern.behavioral.cor.Impl1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Handler {
    private Handler successor;

    public Handler(Handler successor) {
        this.successor = successor;
    }

    public void handleRequest(Request request) {
        if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
