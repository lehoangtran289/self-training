package DesignPattern.creational.singleton;

import lombok.Getter;
import lombok.Setter;

/**
 * a variable declared final is safe to access from multiple threads
 */
@Getter
@Setter
public class SingletonEagerInit {
    private static final SingletonEagerInit INSTANCE = new SingletonEagerInit();
    private String info;

    private SingletonEagerInit() {
        this.info = "Hello World";
    }

    public static SingletonEagerInit getInstance() {
        return INSTANCE;
    }
}
