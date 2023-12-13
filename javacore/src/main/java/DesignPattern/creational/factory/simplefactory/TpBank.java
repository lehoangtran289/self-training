package DesignPattern.creational.factory.simplefactory;

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

    @Override
    public String getName() {
        return name;
    }
}
