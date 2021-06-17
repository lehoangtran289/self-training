package param;

import JunitModels.SlashyDateConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArgumentConversionTest {

    // Implicit Conversion
    @ParameterizedTest
    @CsvSource({"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"}) // Pssing strings
    void someMonths_Are30DaysLongCsv(Month month) {
        final boolean isALeapYear = false;
        assertEquals(30, month.length(isALeapYear));
    }

    // Explicit conversion
    // convert string to LocalDate
    @ParameterizedTest
    @CsvSource({"2018/12/25,2018", "2019/02/11,2019"})
    void getYear_ShouldWorkAsExpected(@ConvertWith(SlashyDateConverter.class) LocalDate date, int expected) {
        assertEquals(expected, date.getYear());
    }
}
