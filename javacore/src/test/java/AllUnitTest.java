import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("JunitModels")
//@SelectClasses({MessageUtilTest.class, HelloWorldTest.class})
public class AllUnitTest {

}
