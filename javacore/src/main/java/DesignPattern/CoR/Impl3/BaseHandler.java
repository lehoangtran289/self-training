package DesignPattern.CoR.Impl3;

import lombok.Data;

@Data
public abstract class BaseHandler implements Handler {
    private Handler next;

    @Override
    public void setNext(Handler cur) {
        this.next = cur;
    }

    @Override
    public void handle(Currency cur) {
        if (cur != null) {
            next.handle(cur);
        }
    }
}
