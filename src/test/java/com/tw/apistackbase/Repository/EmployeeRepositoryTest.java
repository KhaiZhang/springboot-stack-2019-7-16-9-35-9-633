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
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_employee_by_id(){
        List<Employee> employees = employeeRepository.getFirstEmployee();
        Employee employee = employees.stream().findAny().orElse(null);
        long id = employee.getId();
        Employee actualEmployee = employeeRepository.getEmployeeById(id);
        Assertions.assertEquals(employee,actualEmployee);
    }
}