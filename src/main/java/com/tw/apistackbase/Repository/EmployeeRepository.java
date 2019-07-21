package com.tw.apistackbase.Repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component("EmployeeRepository")
public class EmployeeRepository {

    private List<Employee> firstEmployee = new ArrayList<>();
    {
        firstEmployee.add(new Employee("Khai",1,"male"));
        firstEmployee.add(new Employee("Gordon",2,"male"));
        firstEmployee.add(new Employee("Shoron",3,"female"));
        firstEmployee.add(new Employee("Will",4,"male"));
        firstEmployee.add(new Employee("Dillon",5,"male"));
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

    public List<Employee> getEmployeesByGender(String  gender){
        return firstEmployee.stream().filter(element -> gender.equals(element.getGender())).collect(Collectors.toList());
    }

    public Employee addNewEmployee(Employee employee){
        firstEmployee.add(employee);
        return employee;
    }

    public Employee updateEmployeeById(long id , Employee employee){
        Employee updateEmployee = new Employee();
        for (Employee element:firstEmployee) {
            if(element.getId() == id ){
                if(employee.getGender() != null ) element.setGender(employee.getGender());
                if(employee.getName() != null ) element.setName(employee.getName());
                updateEmployee = element;
                break;
            }
        }
        return updateEmployee;
    }
}
