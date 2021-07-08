package com.vds.entity.school;

import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "student"
    )
    @ToString.Exclude
    private Set<Book> books = new HashSet<>();

    public Student(String fullName) {
        this.fullName = fullName;
    }

    public void addBooks(Book... books) {
        this.books.addAll(Arrays.asList(books));
        Arrays.asList(books).forEach(b -> b.setStudent(this));
    }
}
