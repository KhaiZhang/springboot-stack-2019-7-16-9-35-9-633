package com.tw.apistackbase.model;

import org.springframework.context.annotation.Bean;

public class Employee {
    private String name;
    private  long id;
    private  String gender;

    public Employee(){}

    public Employee(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public Employee(String name, long id, String gender) {
        this.name = name;
        this.id = id;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
