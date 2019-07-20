package com.tw.apistackbase.model;

import java.util.List;

public class Company {
    private long id;
    private String companyName;
    private List<Employee> employees;
    private long empployeesNumber;

    public Company(){

    }
    public Company(long id,String companyName, List<Employee> employees, long empployeesNumber) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;
        this.empployeesNumber = empployeesNumber;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public long getEmpployeesNumber() {
        return empployeesNumber;
    }

    public void setEmpployeesNumber(long empployeesNumber) {
        this.empployeesNumber = empployeesNumber ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
