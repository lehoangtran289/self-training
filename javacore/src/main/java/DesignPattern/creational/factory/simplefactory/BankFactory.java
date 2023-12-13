package DesignPattern.creational.factory.simplefactory;

import java.util.HashMap;

public class BankFactory {
    private static final BankFactory INSTANCE = new BankFactory();
    private final HashMap<BankType, Bank> map = new HashMap<>();

    private BankFactory() {
        registerBank(BankType.TPBANK, new TpBank());
        registerBank(BankType.VIETCOMBANK, new VietcomBank());
    }

    public static BankFactory singleton() {
        return INSTANCE;
    }

    public void registerBank(BankType type, Bank bank) {
        map.put(type, bank);
    }

    public Bank getBank(BankType type) {
        return map.get(type);
    }
}
