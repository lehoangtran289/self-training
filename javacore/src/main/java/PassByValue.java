import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PassByValue {
    public static void main(String[] args) {
        List<String> ret = new ArrayList<>();
        List<String> toBeAdded = Arrays.asList("1", "2", "3");
        foo(toBeAdded, ret);
        System.out.println(ret);
    }

    public static void foo(List<String> lst, List<String> ret) {
        ret.addAll(lst);
    }
}
