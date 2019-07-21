package com.tw.apistackbase.Repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
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


    @Test
    public void shoulu_return_company_by_id() {
        List<Company> companies = companyRepository.getCompanies();
        Company company = companies.get(0);
        Company actualCompany = companyRepository.getCompanyById(company.getId());

        Assertions.assertEquals(company,actualCompany);
    }



    @Test
    public void shoulu_return_employess_by_company_id() {
        List<Company> companies = companyRepository.getCompanies();
        Company company = companies.get(0);
        List<Employee> employees = companyRepository.getEmployeesByCompanyId(company.getId());

        Assertions.assertEquals(company.getEmployees(),employees);
    }


    @Test
    public void should_add_a_new_company() {
        Company company = new Company(5, "360", employeeRepository.getFirstEmployee(), 1);
        Company newCompany = companyRepository.addNewCompany(company);
        Assertions.assertEquals(newCompany.getId(),5);
    }

    //    @Test
//    public void should_update_company_information_by_id() {
//        Company company = new Company(1, "360", employeeRepository.getFirstEmployee(), 1);
//        Company newCompany = companyRepository.updateCompanyById(1, company);
//
//        Assertions.assertEquals("360",newCompany.getCompanyName());
//    }


    @Test
    public void should_delete_employees_by_company_id() {
        Company company = companyRepository.getCompanies().stream().findAny().get();
        long id = company.getId();
        companyRepository.deleteEmployeesByCompanyId(id);
        Assertions.assertEquals(null,company.getEmployees());

    }
}