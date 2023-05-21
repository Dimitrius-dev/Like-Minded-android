package com.dimitriusdev.models;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import org.springframework.data.annotation.Id;

//@Getter
//@Setter
public class Project {

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        //this.authorCustomer = authorCustomer;
    }

    private Long id;

    private String name;

    private String description;

    private Customer authorCustomer;

    private Set<Customer> customerSubs = new HashSet<>();


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

    public Customer getAuthorCustomer() {
        return authorCustomer;
    }

    public void setAuthorCustomer(Customer authorCustomer) {
        this.authorCustomer = authorCustomer;
    }

    public Set<Customer> getCustomerSubs() {
        return customerSubs;
    }

    public void setCustomerSubs(Set<Customer> customerSubs) {
        this.customerSubs = customerSubs;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", authorCustomer=" + authorCustomer +
                ", customerSubs=" + customerSubs +
                '}';
    }
    //private List<> String password;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Thing> things;
}