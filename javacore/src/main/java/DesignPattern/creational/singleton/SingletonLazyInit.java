package DesignPattern.creational.singleton;

import lombok.Getter;
import lombok.Setter;

/**
 * Not thread-safe
 */
@Getter
@Setter
public class SingletonLazyInit {
    private static SingletonLazyInit instance;
    private String info;

    private SingletonLazyInit() {
        this.info = "Hello World";
    }

    public static SingletonLazyInit getInstance() {
        if (instance == null) {
            instance = new SingletonLazyInit();
        }
        return instance;
    }
}
