package Exception;

import java.io.FileNotFoundException;
import java.io.IOException;

public class examples {
    public static void main(String[] args) throws IOException {
        try {
            testException(-5);
            testException(-10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Releasing resources");
        }
        throw new FileNotFoundException();
    }

    public static void testException(int i) throws FileNotFoundException, IOException {
        if (i < 0) {
            throw new FileNotFoundException("Negative Integer " + i);
        } else if (i > 10) {
            throw new IOException("Only supported for index 0 to 10");
        }
    }
}
