package DesignPattern.creational.singleton;

import lombok.Getter;
import lombok.Setter;

/**
 * Double Check Locking Singleton
 */
@Getter
@Setter
public class SingletonThreadSafe {
    private static volatile SingletonThreadSafe instance;
    private String info;

    private SingletonThreadSafe() {
        this.info = "Hello World";
    }

    public static SingletonThreadSafe getInstance() {
        // Do something...
        if (instance == null) {
            synchronized (SingletonThreadSafe.class) {
                // Re-check...
                if (instance == null) {
                    instance = new SingletonThreadSafe();
                }
            }
        }
        // Do something...
        return instance;
    }
}
