package JunitModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person {
    private String firstName;
    private String middleName;
    private String lastName;

    public String fullName() {
        if (middleName == null || middleName.trim().isEmpty()) {
            return String.format("%s %s", firstName, lastName);
        }

        return String.format("%s %s %s", firstName, middleName, lastName);
    }
}
