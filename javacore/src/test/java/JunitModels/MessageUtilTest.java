package JunitModels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MessageUtilTest {

    @Test
    public void testPrintMessage() {
        String message = "Henlo world";
        MessageUtil util = new MessageUtil(message);
        assertEquals(message, util.printMsg());
        assertNotEquals("Henlo mars", util.printMsg());
    }
}
