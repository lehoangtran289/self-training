package DesignPattern.CoR.Impl2;

import lombok.Data;

import java.util.Scanner;

@Data
public class ATMDispenseChain {
    private DispenseChain c1;

    public ATMDispenseChain() {
        this.c1 = new Dollar50Dispenser(new Dollar20Dispenser(new Dollar10Dispenser(new Dollar1Dispenser())));
    }

    public static void main(String[] args) {
        ATMDispenseChain atmDispenser = new ATMDispenseChain();
        int amount = 0;
        System.out.println("Enter amount to dispense");
        Scanner input = new Scanner(System.in);
        amount = input.nextInt();
        atmDispenser.c1.dispense(new Currency(amount));
    }
}
