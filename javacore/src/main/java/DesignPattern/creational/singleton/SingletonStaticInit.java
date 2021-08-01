package DesignPattern.creational.singleton;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingletonStaticInit {
    private static final SingletonStaticInit INSTANCE;
    private String info;

    private SingletonStaticInit() {
        this.info = "Hello World";
    }

    // Static block initialization for exception handling
    static {
        try {
            INSTANCE = new SingletonStaticInit();
        } catch (Exception ex) {
            throw new RuntimeException("Exception occurs");
        }
    }

    public static SingletonStaticInit getInstance() {
        return INSTANCE;
    }
}
