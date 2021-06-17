package JunitModels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacocoTest {

    @Test
    public void testJacoco() {
        JacoMessageBuilder obj = new JacoMessageBuilder();
        assertEquals("Hello cheems", obj.getMessage("cheems"));
    }
}
