package JunitModels;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Stream;

class VariableArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<VariableSource> {

    private String variableName;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        Optional<Class<?>> testClass = context.getTestClass();
        Optional<Field> field = testClass.map(this::getField);
        Optional<Stream<Arguments>> argumentsStream = field.map(this::getValue);
        return context.getTestClass()
                .map(this::getField)
                .map(this::getValue)
                .orElseThrow(() ->
                        new IllegalArgumentException("Failed to load test arguments"));
    }

    @Override
    public void accept(VariableSource variableSource) {
        variableName = variableSource.value();
    }

    private Field getField(Class<?> clazz) {
        try {
            return clazz.getDeclaredField(variableName);
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private Stream<Arguments> getValue(Field field) {
        Object value = null;
        try {
            value = field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value == null ? null : (Stream<Arguments>) value;
    }
}
