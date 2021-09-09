package DesignPattern.creational.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class BankAccount {
    private final String name; // required
    private final String accountNumber; // required
    private final String address;
    private final String email;
    private final boolean newsletter;
    private final boolean mobileBanking;

    public static BankAccountConcreteBuilder builder(String name, String accountNumber) {
        return new BankAccountConcreteBuilder(name, accountNumber);
    }
}
