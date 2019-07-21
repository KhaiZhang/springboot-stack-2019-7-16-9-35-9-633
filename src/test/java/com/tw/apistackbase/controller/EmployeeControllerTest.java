package com.tw.apistackbase.controller;

import com.google.gson.Gson;
import com.tw.apistackbase.Repository.EmployeeRepository;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_employees() throws Exception{
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("dox",2));
        employees.add(new Employee("coc",3));
        when(employeeRepository.getFirstEmployee()).thenReturn(employees);
        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(employees)));
    }

    @Test
    public void should_return_employee_by_id() throws Exception {
        Employee employee = new Employee("boss",1);
        when(employeeRepository.getEmployeeById(anyLong())).thenReturn(employee);
        mockMvc.perform(get("/employees/{id}", employee.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(employee)));
    }

    @Test
    public void should_return_employee_by_page_and_pageSize() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Khai",1));
        employees.add(new Employee("Gordon",2));
        employees.add(new Employee("Shoron",3));
        employees.add(new Employee("Will",4));
        employees.add(new Employee("Dillon",5));
        when(employeeRepository.getFirstEmployee()).thenReturn(employees);
        mockMvc.perform(get("/employees").param("page","1").param("pageSize","5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(employees)));
    }

    @Test
    public void should_return_employees_by_gender() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Khai",1,"male"));
        employees.add(new Employee("Gordon",2,"male"));
        employees.add(new Employee("Will",4,"male"));
        employees.add(new Employee("Dillon",5,"male"));
        when(employeeRepository.getEmployeesByGender(anyString())).thenReturn(employees);
        mockMvc.perform(get("/employees").param("gender","male"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(employees)));
    }

}