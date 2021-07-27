package DesignPattern.Adapter;

import lombok.AllArgsConstructor;

/**
 * Existing class
 */
@AllArgsConstructor
public class RoundPeg implements Peg{
    private final double radius;

    @Override
    public double getRadius() {
        return this.radius;
    }
}
