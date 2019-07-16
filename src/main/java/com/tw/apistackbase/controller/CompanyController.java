package com.tw.apistackbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public ResponseEntity getCompanies(){
        return ResponseEntity.ok(companyRepository.getCompanies());
    }

    @GetMapping("/companies/{empployeesNumber}")
    public ResponseEntity getCompanyByempployeesNumber(@PathVariable long empployeesNumber){
        return ResponseEntity.ok(companyRepository.getCompanies()
                .stream()
                .filter(v -> v.getEmpployeesNumber() == empployeesNumber)
                .findFirst()
                .orElse(null)
                );
    }

    @GetMapping("/companies/{empployeesNumber}/employees")
    public ResponseEntity getemployeesByempployeesNumber(@PathVariable long empployeesNumber){
        return ResponseEntity.ok(companyRepository.getCompanies()
                .stream()
                .filter(v -> v.getEmpployeesNumber() == empployeesNumber)
                .findFirst()
                .orElse(null).getEmployees()
        );
    }

    @PutMapping("/companies/{empployeesNumber}")
    public ResponseEntity updateCompanyInformation(@PathVariable long empployeesNumber,@RequestBody Company company){
        return ResponseEntity.ok(companyRepository.updateCompanyByempployeesNumber(empployeesNumber,company));
    }
}
