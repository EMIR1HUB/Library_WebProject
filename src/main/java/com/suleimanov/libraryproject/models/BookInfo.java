package com.suleimanov.libraryproject.models;

public class BookInfo {
    private Long id;
    private Long personId;
    private String name;
    private String author;
    private Integer year;

    private String pathFileName;

    public BookInfo(){}

    public BookInfo(Long id, Long personId, String name, String author, Integer year, String pathFileName) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.author = author;
        this.year = year;
        this.pathFileName = pathFileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPathFileName() {
        return pathFileName;
    }

    public void setPathFileName(String pathFileName) {
        this.pathFileName = pathFileName;
    }
}
