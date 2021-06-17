package param;

import JunitModels.Person;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArgumentAggregatorAndAccessorTest {
    /**
     *  encapsulate all passed arguments into an instance of ArgumentsAccessor and retrieve arguments by index and type.
     */
    @ParameterizedTest
    @CsvSource({"Isaac,,Newton,Isaac Newton", "Charles,Robert,Darwin,Charles Robert Darwin"})
    void fullName_ShouldGenerateTheExpectedFullName(ArgumentsAccessor argumentsAccessor) {
        String firstName = argumentsAccessor.getString(0);
        String middleName = (String) argumentsAccessor.get(1);
        String lastName = argumentsAccessor.get(2, String.class);
        String expectedFullName = argumentsAccessor.getString(3);

        Person person = new Person(firstName, middleName, lastName);
        assertEquals(expectedFullName, person.fullName());
    }

    @ParameterizedTest
    @CsvSource({"Isaac Newton,Isaac,,Newton", "Charles Robert Darwin,Charles,Robert,Darwin"})
    void fullName_ShouldGenerateTheExpectedFullName(
            String expectedFullName,
            @AggregateWith(PersonAggregator.class) Person person
    ) {
        assertEquals(expectedFullName, person.fullName());
    }
}

class PersonAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
        return new Person(accessor.getString(1), accessor.getString(2), accessor.getString(3));
    }
}
