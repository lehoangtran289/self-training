package entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user2")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User2 {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public User2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void setId(int id) {
        this.id = id;
    }
}
