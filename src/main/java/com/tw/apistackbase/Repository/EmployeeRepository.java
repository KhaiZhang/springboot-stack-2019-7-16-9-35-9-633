package com.tw.apistackbase.Repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component("EmployeeRepository")
public class EmployeeRepository {

    private List<Employee> firstEmployee = new ArrayList<>();
    {
        firstEmployee.add(new Employee("Khai",1));
        firstEmployee.add(new Employee("Gordon",2));
        firstEmployee.add(new Employee("Shoron",3));
        firstEmployee.add(new Employee("Will",4));
        firstEmployee.add(new Employee("Dillon",5));
    }
            ;

    private List<Employee> secondEmployee = new ArrayList<>();
    {
        secondEmployee.add(new Employee("Kar",6));
        secondEmployee.add( new Employee("Gox",7));
        secondEmployee.add(new Employee("Zed",8));
        secondEmployee.add( new Employee("Akri",9));
        secondEmployee.add(new Employee("Ryte",10));
    }

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

    public Employee getEmployeeById(long id){
        return firstEmployee.stream().filter(element -> element.getId() == id).findFirst().orElse(null);
    }
}
