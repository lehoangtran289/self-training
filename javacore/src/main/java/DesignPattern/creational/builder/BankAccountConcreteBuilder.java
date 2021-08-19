package DesignPattern.creational.builder;

import lombok.ToString;

@ToString
public class BankAccountConcreteBuilder {
    private final String name; // required
    private final String accountNumber; // required
    private String address;
    private String email;
    private boolean newsletter;
    private boolean mobileBanking;

    public BankAccountConcreteBuilder(String name, String accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public BankAccountConcreteBuilder address(String address) {
        this.address = address;
        return this;
    }

    public BankAccountConcreteBuilder email(String email) {
        this.email = email;
        return this;
    }

    public BankAccountConcreteBuilder newsletter(boolean newsletter) {
        this.newsletter = newsletter;
        return this;
    }

    public BankAccountConcreteBuilder mobileBanking(boolean mobileBanking) {
        this.mobileBanking = mobileBanking;
        return this;
    }

    public BankAccount build() {
        validateUserObject();
        return new BankAccount(
                this.name, this.accountNumber,
                this.address, this.email,
                this.newsletter, this.mobileBanking);
    }

    private void validateUserObject() {
        // Do some basic validations to check
    }
}
