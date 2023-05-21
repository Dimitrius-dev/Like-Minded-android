package com.dimitriusdev.models;


import android.util.ArraySet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import org.springframework.data.annotation.Id;

//@Getter
//@Setter

public class Customer {

    private Long id;

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
        this.projects = new ArrayList<>();
        this.projectSubs = new ArraySet<>();
    }

    private String login;

    private String password;

    private List<Project> projects;

    private Set<Project> projectSubs = new HashSet<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Set<Project> getProjectSubs() {
        return projectSubs;
    }

    public void setProjectSubs(Set<Project> projectSubs) {
        this.projectSubs = projectSubs;
    }

    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Thing> things;
}
