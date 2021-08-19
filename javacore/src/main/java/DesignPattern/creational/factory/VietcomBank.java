package DesignPattern.creational.factory;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class VietcomBank extends Bank {
    private String name;

    static {
        BankFactory.singleton().registerBank(BankType.VIETCOMBANK, new VietcomBank());
    }

    @Override
    public String getName() {
        return name;
    }
}
