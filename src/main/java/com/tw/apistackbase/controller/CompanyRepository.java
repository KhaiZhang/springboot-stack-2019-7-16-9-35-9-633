package com.tw.apistackbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component("CompanyRepository")
public class CompanyRepository {


        private EmployeeRepository employeeRepository = new EmployeeRepository();

        private List<Company> companies = Arrays.asList(
                new Company("TX",employeeRepository.getFirstEmployee(),1),
                new Company("alibaba",employeeRepository.getSecondEmployee(),2)
        );

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public Company updateCompanyByempployeesNumber(long empployeesNumber,Company company){
        for (Company element:companies) {
            if(element.getEmpployeesNumber() == empployeesNumber){
                element.setcompanyName(company.getcompanyName());
                element.setEmployees(company.getEmployees());
                element.setEmpployeesNumber(company.getEmpployeesNumber());
                company=element;
            }
        }
        return company;
    }
}
