package DesignPattern.creational.factory;

import java.util.HashMap;

public class BankFactory {
    private static final BankFactory INSTANCE = new BankFactory();
    private HashMap<BankType, Bank> map = new HashMap<>();

    private BankFactory() {}

    public static BankFactory singleton() {
        return INSTANCE;
    }

    public void registerBank(BankType type, Bank bank) {
        map.put(type, bank);
    }

    public Bank createBank(BankType type) {
        return map.get(type);
    }
}
