package com.tw.apistackbase.controller;

import com.google.gson.Gson;
import com.tw.apistackbase.Repository.CompanyRepository;
import com.tw.apistackbase.Repository.EmployeeRepository;
import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.tags.Param;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_companies() throws Exception {
        List<Employee> firstEmployee = employeeRepository.getFirstEmployee();
        List<Employee> secondEmployee = employeeRepository.getSecondEmployee();
        Company firstCompany = new Company(1,"TX", firstEmployee, 1);
        Company secondCompany = new Company(2,"alibaba", secondEmployee, 2);
        List<Company> companies = new ArrayList<>();
        companies.add(firstCompany);
        companies.add(secondCompany);
        Gson gson = new Gson();
        when(companyRepository.getCompanies()).thenReturn(companies);
        mockMvc.perform(get("/companies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(companies)));
    }

    @Test
    public void should_return_companies_by_id() throws Exception {
        List<Employee> firstEmployee = employeeRepository.getFirstEmployee();
        Company company = new Company(4,"微软", firstEmployee, 4);
        when(companyRepository.getCompanyById(anyLong())).thenReturn(company);
        mockMvc.perform(get("/companies/{id}", company.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(company)));
    }

    @Test
    public void should_return_companies_by_page_and_pageSize() throws Exception {
        List<Company> companies = companyRepository.getCompanies();
        when(companyRepository.getCompanies()).thenReturn(companies);
        mockMvc.perform(get("/companies").param("page","1").param("pageSize","3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(companies)));
    }

    @Test
    public void should_add_a_new_company() throws Exception {
        Company company = new Company(5, "360", employeeRepository.getFirstEmployee(), 1);

        when(companyRepository.addNewCompany(any(Company.class))).thenReturn(company);
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(company)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(company.getId()));
    }

    @Test
    public void should_return_employess_by_company_id() throws Exception {
        Company company = new Company(5, "360", employeeRepository.getFirstEmployee(), 1);
        when(companyRepository.getEmployeesByCompanyId(anyLong())).thenReturn(company.getEmployees());
        mockMvc.perform(get("/companies/{id}/employees",company.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(company.getEmployees())));
    }



    @Test
    public void should_return_employees_by_employeesNumber() throws Exception {
        mockMvc.perform(get("/companies/1/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Khai\",\"id\":1},{\"name\":\"Gordon\",\"id\":2},{\"name\":\"Shoron\",\"id\":3},{\"name\":\"Will\",\"id\":5},{\"name\":\"Dillon\",\"id\":5}]"));
    }
}