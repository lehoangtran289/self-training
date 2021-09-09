package DesignPattern.structural.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Client
 */
@Data
@AllArgsConstructor
public class RoundHole {
    private double radius;

    public boolean fits(Peg peg) {
        return this.radius >= peg.getRadius();
    }
}
