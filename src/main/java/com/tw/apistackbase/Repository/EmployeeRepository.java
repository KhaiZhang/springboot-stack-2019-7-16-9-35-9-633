package com.tw.apistackbase.Repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component("EmployeeRepository")
public class EmployeeRepository {

    private List<Employee> firstEmployee = Arrays.asList(new Employee("Khai",1),
            new Employee("Gordon",2),new Employee("Shoron",3),
            new Employee("Will",5),new Employee("Dillon",5));

    private List<Employee> secondEmployee =  Arrays.asList(new Employee("Kar",6),
            new Employee("Gox",7),new Employee("Zed",8),
            new Employee("Akri",9),new Employee("Ryte",10));

    public List<Employee> getFirstEmployee() {
        return firstEmployee;
    }

    public void setFirstEmployee(List<Employee> firstEmployee) {
        this.firstEmployee = firstEmployee;
    }

    public List<Employee> getSecondEmployee() {
        return secondEmployee;
    }

    public void setSecondEmployee(List<Employee> secondEmployee) {
        this.secondEmployee = secondEmployee;
    }
}
