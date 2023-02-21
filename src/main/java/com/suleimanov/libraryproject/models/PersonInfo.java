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

    @Pattern(regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",
            message = "Адрес должен быть указан в формате name@domain.***")
    private String email;   // "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"

    @Pattern(regexp = "^(7)\\d{10}$", message = "Номер должен начинаться с 7 и диапазон 10 чисел")
    private String phone;


    public PersonInfo() { }

    public PersonInfo(Long id, String name, String lastName, String patronymic, int dateBirth, String email, String phone) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateBirth = dateBirth;
        this.email = email;
        this.phone = phone;
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

    public String getFullName(){return lastName + " " + name + " " + patronymic; }

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
