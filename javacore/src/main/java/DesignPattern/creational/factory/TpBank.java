package DesignPattern.creational.factory;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class TpBank extends Bank {
    private String name;

    static {
        BankFactory.singleton().registerBank(BankType.TPBANK, new TpBank());
    }

    @Override
    public String getName() {
        return name;
    }
}
