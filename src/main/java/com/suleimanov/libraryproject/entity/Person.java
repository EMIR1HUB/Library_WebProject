//package com.suleimanov.libraryproject.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "person")
//public class Person {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @Column(name = "full_name", length = 100, nullable = false)
//    private String fullName;
//
//    @Column(name = "date_birth")
//    private int dateBirth;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public int getDateBirth() {
//        return dateBirth;
//    }
//
//    public void setDateBirth(int dateBirth) {
//        this.dateBirth = dateBirth;
//    }
//}
