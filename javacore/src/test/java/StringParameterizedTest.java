import JunitModels.StringChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringParameterizedTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "\n"})
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
        assertTrue(StringChecker.isBlank(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void isBlank_ShouldReturnTrueForNullInputs(String input) {
        assertTrue(StringChecker.isBlank(input));
    }
}
