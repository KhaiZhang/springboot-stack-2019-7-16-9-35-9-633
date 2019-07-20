package com.tw.apistackbase.model;

import java.util.List;

public class Company {
    private String companyName;
    private List<Employee> employees;
    private long empployeesNumber;

    public Company(){

    }
    public Company(String companyName, List<Employee> employees, long empployeesNumber) {
        this.companyName = companyName;
        this.employees = employees;
        this.empployeesNumber = empployeesNumber;
    }

    public String getcompanyName() {
        return companyName;
    }

    public void setcompanyName(String companyName) {
        this.companyName = companyName;
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
}
