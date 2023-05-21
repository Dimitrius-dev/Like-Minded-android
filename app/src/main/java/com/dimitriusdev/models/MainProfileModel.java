package com.dimitriusdev.models;

@Deprecated
public class MainProfileModel {
    private String name;
    private Integer value;

    MainProfileModel(){
        value = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
