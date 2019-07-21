package com.tw.apistackbase.controller;


import com.tw.apistackbase.Repository.EmployeeRepository;
import com.tw.apistackbase.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployeesByPageAndPageSize(@RequestParam(value = "page",defaultValue = "0")int page ,
                                                                        @RequestParam(value = "pageSize"  , defaultValue = "0") int pageSize ,
                                                                         @RequestParam(value = "gender" , defaultValue = "0") String gender){

         if(page ==0 && pageSize ==0 && "0".equals(gender)){
            return ResponseEntity.ok(employeeRepository.getFirstEmployee());
        }
         else if(page != 0 && pageSize != 0){
             int startIndex = (page - 1) * pageSize;
             int endIndex = startIndex + pageSize;
             int size = employeeRepository.getFirstEmployee().size();
             if(endIndex > size ){
                 return ResponseEntity.ok(employeeRepository.getFirstEmployee().subList(startIndex,size));
             }
             return ResponseEntity.ok(employeeRepository.getFirstEmployee().subList(startIndex,endIndex));
        }
         else {
            return ResponseEntity.ok(employeeRepository.getEmployeesByGender(gender));
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(employeeRepository.getEmployeeById(id));
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeRepository.addNewEmployee(employee));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable(value = "id")long id,@RequestBody Employee employee){
        return ResponseEntity.ok(employeeRepository.updateEmployeeById(id,employee));
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable(value = "id")long id){
        return employeeRepository.deleteEmployeeById(id);
    }
}
