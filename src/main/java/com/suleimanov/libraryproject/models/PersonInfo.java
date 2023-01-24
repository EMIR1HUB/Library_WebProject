package com.suleimanov.libraryproject.models;

// Предоставляет данные одной записи оператора запроса


import jakarta.validation.constraints.*;

public class PersonInfo {

    private Long id;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Допустимый размер от 2 до 100")
    @Pattern(regexp = "^([А-Я]{1}[а-яё]{1,99}|[A-Z]{1}[a-z]{1,99})$", message = "Не допустимый символ в имени")
    private String name;

    @NotEmpty(message = "Фамилия не должно быть пустым")
    @Size(min = 2, max = 100, message = "Допустимый размер от 2 до 100")
    @Pattern(regexp = "^([А-Я]{1}[а-яё]{1,99}|[A-Z]{1}[a-z]{1,99})$", message = "Не допустимый символ в фамилии")
    private String lastName;

//    @Size(min = 1, max = 100, message = "Допустимый размер от 1 до 100")
    private String patronymic;

    @Min(value = 1900, message = "Не допущенная дата")
    private int dateBirth;


    public PersonInfo() {
    }

    public PersonInfo(Long id, String name, String lastName, String patronymic, int dateBirth) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateBirth = dateBirth;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getFullName(){return lastName + " " + name + " " + patronymic; }
}
