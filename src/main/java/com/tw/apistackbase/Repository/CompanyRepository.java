package com.tw.apistackbase.Repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component("CompanyRepository")
public class CompanyRepository {


        private EmployeeRepository employeeRepository = new EmployeeRepository();

        private List<Company> companies = new ArrayList<Company>();
    {
        companies.add( new Company(1,"TX",employeeRepository.getFirstEmployee(),1));
        companies.add( new Company(2,"alibaba",employeeRepository.getSecondEmployee(),2));
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public Company getCompanyById( long id){
        return companies.stream().filter(company -> company.getId() == id ).findAny().orElse(null);
    }

    public List<Employee> getEmployeesByCompanyId(long id){
        Company company = companies.stream().filter(element -> element.getId() == id).findFirst().orElse(null);
        if(company == null) return null;
        else return company.getEmployees();
    }



    public Company addNewCompany(Company company){
        companies.add(company);
        return company;
    }

    public Company updateCompanyById(long id , Company company){
        Company updateCompany = new Company();
        for (Company element:companies) {
            if(element.getId() == id){
                if(company.getCompanyName() != null) element.setCompanyName(company.getCompanyName());
                if(company.getEmployees() != null) element.setEmployees(company.getEmployees());
                if(company.getEmpployeesNumber() != 0) element.setEmpployeesNumber(company.getEmpployeesNumber());
                updateCompany=element;
                break;
            }
        }
        return updateCompany;
    }

    public Company deleteEmployeesByCompanyId(long id){
        Company company = new Company();
        for (Company element:companies) {
            if(element.getId() == id){
                element.setEmployees(null);
                company=element;
                break;
            }
        }
        return company;
    }
}
