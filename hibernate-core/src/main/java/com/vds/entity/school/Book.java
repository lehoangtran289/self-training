package com.vds.entity.school;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private Student student;

    public Book(String title) {
        this.title = title;
    }
}
