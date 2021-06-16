import org.junit.jupiter.api.*;

public class JunitLifeCycleTest {
    private int i = 0;

    @BeforeAll
    public static void runOneTimeBeforeRunAll() {
        System.out.println("in BeforeAll");
    }

    // setup
    @BeforeEach
    public void runEachTimeBeforeEachTestCase() {
        System.out.println("in BeforeEach");
    }

    // tearDown
    @AfterEach
    public void runEachTimeAfterEachTestCase() {
        System.out.println("in AfterEach");
    }

    @AfterAll
    public static void runOneTimeAfterRunAll() {
        System.out.println("in AfterAll");
    }

    @Test
    @DisplayName("Something smt")
    public void testSomething() {
        System.out.println("Henlo World");
    }

    @Test
    @RepeatedTest(2)
    public void testClick() {
        System.out.println("Repeated Test: " + ++i);
    }

    @Disabled
    public void notYetComplete() {
        System.out.println("Not Complete");
    }
}
