package com.dimitriusdev.models;

@Deprecated
public class ProjectItemModel {
    private String name;
    private String author;
    private Integer idAuthor;

    public ProjectItemModel(String name, String author, Integer idAuthor) {
        this.name = name;
        this.author = author;
        this.idAuthor = idAuthor;
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
}
