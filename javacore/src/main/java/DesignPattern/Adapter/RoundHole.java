package DesignPattern.Adapter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoundHole {
    private double radius;

    public boolean fits(Peg peg) {
        return this.radius >= peg.getRadius();
    }
}
