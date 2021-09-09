package DesignPattern.creational.factory;

/**
 * - creates objects without exposing the instantiation logic to the client.
 * - refers to the newly created object through a common interface
 */
public class Client {

    static {
        try {
            Class.forName("DesignPattern.creational.factory.TpBank");
            Class.forName("DesignPattern.creational.factory.VietcomBank");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Bank tpBank = BankFactory.singleton().createBank(BankType.TPBANK);
        ((TpBank) tpBank).setName("tpBank");
        System.out.println(tpBank);

        Bank vietcomBank = BankFactory.singleton().createBank(BankType.VIETCOMBANK);
        ((VietcomBank) vietcomBank).setName("vietcomBank");
        System.out.println(vietcomBank);
    }
}
