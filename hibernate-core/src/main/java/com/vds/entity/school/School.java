package com.vds.entity.school;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
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
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class School implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "address")
    private String address;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            },
            mappedBy = "school"
    )
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
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
