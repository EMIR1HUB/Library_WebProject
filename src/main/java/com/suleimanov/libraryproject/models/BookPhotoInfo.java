package com.suleimanov.libraryproject.models;

public class BookPhotoInfo {
    private Long id;
    private Long bookId;
    private String pathFileName;

    public BookPhotoInfo(){}

    public BookPhotoInfo(Long id, Long bookId, String pathFileName) {
        this.id = id;
        this.bookId = bookId;
        this.pathFileName = pathFileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getPathFileName() {
        return pathFileName;
    }

    public void setPathFileName(String pathFileName) {
        this.pathFileName = pathFileName;
    }
}
