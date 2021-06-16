package JunitModels;

public class NumberChecker {

    public Boolean validatePrime(final Integer primeNumber) {
        for (int i = 2; i < primeNumber / 2; i++) {
            if (primeNumber % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }
}
