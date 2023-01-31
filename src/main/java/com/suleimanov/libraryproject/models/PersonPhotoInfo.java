package com.suleimanov.libraryproject.models;

public class PersonPhotoInfo {
    private Long id;
    private Long personId;
    private String pathToThePhoto;

    public PersonPhotoInfo(){}

    public PersonPhotoInfo(Long id, Long personId, String pathToThePhoto){
        this.id = id;
        this.personId = personId;
        this.pathToThePhoto = pathToThePhoto;
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

    public String getPathToThePhoto() {
        return pathToThePhoto;
    }

    public void setPathToThePhoto(String pathToThePhoto) {
        this.pathToThePhoto = pathToThePhoto;
    }
}
