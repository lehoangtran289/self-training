package com.vds.entity.school;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student_detail")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private Student student;

    public StudentDetail(String email, String address) {
        this.email = email;
        this.address = address;
    }
}
