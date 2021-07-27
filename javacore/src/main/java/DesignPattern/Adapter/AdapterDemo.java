package DesignPattern.Adapter;

/**
 * Reference: https://refactoring.guru/design-patterns/adapter
 */
public class AdapterDemo {

    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPeg roundPeg = new RoundPeg(5);
        System.out.println(hole.fits(roundPeg));

        SquarePeg smallSquare = new SquarePeg(5);
        SquarePeg largeSquare = new SquarePeg(15);

//         hole.fits(smallSquare); // ERROR -> Not compatible -> need adapter
        SquarePegAdapter smallSquareAdapter = new SquarePegAdapter(smallSquare);
        SquarePegAdapter largeSquareAdapter = new SquarePegAdapter(largeSquare);
        System.out.println(hole.fits(smallSquareAdapter));  // true
        System.out.println(hole.fits(largeSquareAdapter));  // false
    }
}
