package com.vds.springdatajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "school_id")
    @ToString.Exclude
    private School school;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private StudentDetail studentDetail;

    public Student(String fullName) {
        this.fullName = fullName;
    }
}

