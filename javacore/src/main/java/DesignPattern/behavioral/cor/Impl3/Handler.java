package DesignPattern.behavioral.cor.Impl3;

public interface Handler {
    void setNext(Handler h);
    void handle(Currency cur);
}
