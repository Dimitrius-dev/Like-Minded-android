package com.dimitriusdev.models;

public class ProjectModel {
    private String name;
    private String description;
    private CustomerModel authorCustomer;

    public CustomerModel getAuthorCustomer() {
        return authorCustomer;
    }

    public void setAuthorCustomer(CustomerModel authorCustomer) {
        this.authorCustomer = authorCustomer;
    }

    public ProjectModel(String name, String description, CustomerModel authorCustomer) {
        this.name = name;
        this.description = description;
        this.authorCustomer = authorCustomer;
    }

    public ProjectModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
