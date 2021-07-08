package com.vds.entity.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) // com.vds.demo.IdGeneratorExample
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
