package Algo.random;

import java.util.HashMap;
import java.util.Map;

public class DistributedRandomNumberGenerator {
    private Map<Integer, Double> distribution;
    private double distSum;

    public DistributedRandomNumberGenerator() {
        distribution = new HashMap<>();
    }

    public void addNumber(int value, double distribution) {
        if (this.distribution.get(value) != null) {
            distSum -= this.distribution.get(value);
        }
        this.distribution.put(value, distribution);
        distSum += distribution;
    }

    public int getDistributedRandomNumber() {
        double rand = Math.random();
        double ratio = 1.0f / distSum;
        double tempDist = 0;
        for (Integer i : distribution.keySet()) {
            tempDist += distribution.get(i);
            if (rand / ratio <= tempDist) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        DistributedRandomNumberGenerator drng = new DistributedRandomNumberGenerator();
        drng.addNumber(1, 0.2d);
        drng.addNumber(2, 0.3d);
        drng.addNumber(3, 0.5d);

        int testCount = 1000000;

        HashMap<Integer, Double> test = new HashMap<>();

        for (int i = 0; i < testCount; i++) {
            int random = drng.getDistributedRandomNumber();
            test.put(random, (test.get(random) == null) ? (1d / testCount) : test.get(random) + 1d / testCount);
        }

        System.out.println(test.toString());
    }

    /**
     * Random with weight
     * //        for (double r = RandomUtils.nextDouble(); rdIndex < toBeRandomized.size() - 1; ++rdIndex) {
     * //            r -= toBeRandomized.get(rdIndex).getDistribution();
     * //            if (r <= 0.0) break;
     * //        }
     */
}
