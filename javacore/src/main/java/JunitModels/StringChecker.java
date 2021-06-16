package JunitModels;

public class StringChecker {
    public static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }

}
