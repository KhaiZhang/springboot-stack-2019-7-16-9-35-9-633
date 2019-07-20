package com.tw.apistackbase.Repository;

import com.tw.apistackbase.model.Company;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyRepositoryTest {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public void getCompanies() {
    }


    void setCompanies() {
    }

    @Test
    public void shoulu_return_company_by_id() {
        List<Company> companies = companyRepository.getCompanies();
        Company company = companies.get(0);
        Company actualCompany = companyRepository.getCompanyById(company.getId());

        Assertions.assertEquals(company,actualCompany);
    }


    void updateCompanyByempployeesNumber() {
    }


    void addNewCompany() {
    }


    void deleteCompany() {
    }
}