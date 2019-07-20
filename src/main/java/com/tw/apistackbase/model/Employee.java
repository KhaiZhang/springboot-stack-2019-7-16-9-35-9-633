package com.tw.apistackbase.model;

import org.springframework.context.annotation.Bean;

public class Employee {
    private String name;
    private  long id;

    public Employee(){}

    public Employee(String name, long id) {
        this.name = name;
        this.id = id;
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
}
