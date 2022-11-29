package com.grupp17.webserviceproject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Card")
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_Name", nullable = false)
    private String firstName;
    @Column(name = "last_Name", nullable = false)
    private String lastName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "description")
    private String description;

    public Card(String firstName, String lastName, int age, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.description = description;
    }
}
