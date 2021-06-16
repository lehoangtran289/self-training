package JunitModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageUtil {
    private String message;

    public String printMsg() {
        System.out.println(message);
        return message;
    }
}
