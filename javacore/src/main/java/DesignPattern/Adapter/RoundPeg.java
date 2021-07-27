package DesignPattern.Adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundPeg implements Peg{
    private final double radius;

    @Override
    public double getRadius() {
        return this.radius;
    }
}
