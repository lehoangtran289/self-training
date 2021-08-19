package DesignPattern.creational.builder;

/**
 * Builder is a creational design pattern that separate the construction of a complex object from its
 * representation so that the same construction process can create different representations.
 * (-) duplicate code when copying exactly model's properties into builder class
 */
public class Client {

    public static void main(String[] args) {
        BankAccount account1 = BankAccount.builder("hoangtl", "123123")
                .email("hoangtl@gmail.com")
                .mobileBanking(true)
                .build();
        BankAccount account2 = BankAccount.builder("hoangtl2", "10000")
                .address("123 Test Street")
                .newsletter(true)
                .build();
        System.out.println(account1);
        System.out.println(account2);
    }
}
