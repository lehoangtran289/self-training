package entity.school;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "school")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "school_name")
    private String schoolName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private List<Student> students;

    public School(String schoolName) {
        this.schoolName = schoolName;
    }
}
