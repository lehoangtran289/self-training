package DesignPattern.creational.factory.simplefactory;

/**
 * - creates objects without exposing the instantiation logic to the client.
 * - refers to the newly created object through a common interface
 */
public class Client {
    public static void main(String[] args) {
        TpBank tpBank = (TpBank) BankFactory.singleton().getBank(BankType.TPBANK);
        tpBank.setName("tpBank");
        System.out.println(tpBank);

        VietcomBank vietcomBank = (VietcomBank) BankFactory.singleton().getBank(BankType.VIETCOMBANK);
        vietcomBank.setName("vietcomBank");
        System.out.println(vietcomBank);
    }
}
