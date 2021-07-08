package com.vds.entity.school;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "school")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "address")
    private String address;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            },
            mappedBy = "school"
    )
    @Cascade(org.hibernate.annotations.CascadeType.LOCK)
    @ToString.Exclude
    private Set<Student> students = new HashSet<>();

    public School(String schoolName, String address) {
        this.schoolName = schoolName;
        this.address = address;
    }

    public void addStudents(Student... students) {
        this.students.addAll(Arrays.asList(students));
        Arrays.asList(students).forEach(s -> s.setSchool(this));
    }
}
