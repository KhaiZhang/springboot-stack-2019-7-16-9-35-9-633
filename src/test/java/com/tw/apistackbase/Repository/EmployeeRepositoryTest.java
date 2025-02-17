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
import java.util.stream.Collectors;

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

    @Test
    public void should_return_employees_by_gender(){
        List<Employee> employees = employeeRepository.getFirstEmployee();
        List<Employee> maleEmployees = employees.stream().filter(element -> "male".equals(element.getGender())).collect(Collectors.toList());
        Employee employee = maleEmployees.stream().findAny().orElse(null);
        Assertions.assertEquals(employee.getGender(),"male");
    }

    @Test
    public void should_update_employee(){
        Employee employee= new Employee("dddx", 1, "female");
        Employee updateEmployee = employeeRepository.updateEmployeeById(employee.getId(), employee);
        Assertions.assertEquals(updateEmployee.getName(),employee.getName());
    }

    @Test
    public void should_delete_employee(){
        Employee employee = employeeRepository.getFirstEmployee().stream().findAny().orElse(null);
        String result = employeeRepository.deleteEmployeeById(employee.getId());
        Assertions.assertEquals(result,"");
    }
}