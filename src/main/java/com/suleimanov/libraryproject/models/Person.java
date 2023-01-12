package com.suleimanov.libraryproject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_birth")
    private int dateBirth;


    public Person() {
    }

    public Person(int id, String fullName, int dateBirth) {
        this.id = id;
        this.fullName = fullName;
        this.dateBirth = dateBirth;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
        this.dateBirth = dateBirth;
    }
}
