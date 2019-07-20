package com.tw.apistackbase.controller;

import com.google.gson.Gson;
import com.tw.apistackbase.Repository.CompanyRepository;
import com.tw.apistackbase.Repository.EmployeeRepository;
import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Company firstCompany = new Company("TX", firstEmployee, 1);
        Company secondCompany = new Company("alibaba", secondEmployee, 2);
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
    public void should_return_companies_by_employeesNumber() throws Exception {

        mockMvc.perform(get("/companies/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"companyName\":\"TX\",\"employees\":[{\"name\":\"Khai\",\"id\":1},{\"name\":\"Gordon\",\"id\":2},{\"name\":\"Shoron\",\"id\":3},{\"name\":\"Will\",\"id\":5},{\"name\":\"Dillon\",\"id\":5}],\"empployeesNumber\":1}"));
    }

    @Test
    public void should_return_employees_by_employeesNumber() throws Exception {
        mockMvc.perform(get("/companies/1/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Khai\",\"id\":1},{\"name\":\"Gordon\",\"id\":2},{\"name\":\"Shoron\",\"id\":3},{\"name\":\"Will\",\"id\":5},{\"name\":\"Dillon\",\"id\":5}]"));
    }
}