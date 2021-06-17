package param;

import JunitModels.BlankStringsArgumentsProvider;
import JunitModels.StringChecker;
import JunitModels.VariableSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.platform.commons.util.StringUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
        assertEquals(expected, StringChecker.isBlank(input));
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not blank", false)
        );
    }

    // JUnit will search for a source method with the same name as the test method.
    @ParameterizedTest
    @MethodSource
    void isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument(String input) {
        assertTrue(StringUtils.isBlank(input));
    }

    private static Stream<String> isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument() {
        return Stream.of(null, "", "  ");
    }

    @ParameterizedTest
    @CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')
    void toLowerCase_ShouldGenerateTheExpectedLowercaseValue(String input, String expected) {
        String actualValue = input.toLowerCase();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @ArgumentsSource(BlankStringsArgumentsProvider.class)
    void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProvider(String input) {
        assertTrue(StringUtils.isBlank(input));
    }

    // load the test arguments from a static variable
    public static Stream<Arguments> arguments = Stream.of(
            Arguments.of(null, true), // null strings should be considered blank
            Arguments.of("", true),
            Arguments.of("  ", true),
            Arguments.of("not blank", false)
    );

    @ParameterizedTest
    @VariableSource("arguments")
    void isBlank_ShouldReturnTrueForNullOrBlankStringsVariableSource(String input, boolean expected) {
        assertEquals(expected, StringUtils.isBlank(input));
    }
}