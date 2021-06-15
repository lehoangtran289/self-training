package DesignPattern.CoR.Impl3;

public interface Handler {
    void setNext(Handler h);
    void handle(Currency cur);
}
