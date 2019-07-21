package com.tw.apistackbase.controller;

import com.tw.apistackbase.Repository.CompanyRepository;
import com.tw.apistackbase.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public ResponseEntity getCompanies(@RequestParam(value = "page",defaultValue = "0")int page ,
                                       @RequestParam(value = "pageSize"  , defaultValue = "0") int pageSize){
        if(page ==0 && pageSize ==0){
            return ResponseEntity.ok(companyRepository.getCompanies());
        }
        else {
            int startIndex = (page - 1) * pageSize;
            int endIndex = startIndex + pageSize;
            int size = companyRepository.getCompanies().size();
            if(endIndex > size ){
                return ResponseEntity.ok(companyRepository.getCompanies().subList(startIndex,size));
            }
            return ResponseEntity.ok(companyRepository.getCompanies().subList(startIndex,endIndex));
        }

    }

    @GetMapping("/companies/{id}")
    public ResponseEntity getCompanyById(@PathVariable long id){
        return ResponseEntity.ok(companyRepository.getCompanyById(id));
    }

    @GetMapping("/companies/{id}/employees")
    public ResponseEntity getemployeesById(@PathVariable long id){
        return ResponseEntity.ok(companyRepository.getEmployeesByCompanyId(id));
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity updateCompanyInformation(@PathVariable long id,@RequestBody Company company){
        return ResponseEntity.ok(companyRepository.updateCompanyById(id,company));
    }

    @PostMapping("/companies")
    public ResponseEntity addCompany(@RequestBody Company company){
        return ResponseEntity.ok(companyRepository.addNewCompany(company));
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity deleteEmployeesByCompanyId(@PathVariable long id){
        return ResponseEntity.ok(companyRepository.deleteEmployeesByCompanyId(id));
    }
}
