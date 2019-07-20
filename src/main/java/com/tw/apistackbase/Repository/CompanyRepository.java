package com.tw.apistackbase.Repository;

import com.tw.apistackbase.model.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component("CompanyRepository")
public class CompanyRepository {


        private EmployeeRepository employeeRepository = new EmployeeRepository();

        private List<Company> companies = new ArrayList<Company>();
    {
        companies.add( new Company("TX",employeeRepository.getFirstEmployee(),1));
        companies.add( new Company("alibaba",employeeRepository.getSecondEmployee(),2));
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public Company updateCompanyByempployeesNumber(long empployeesNumber,Company company){
        for (Company element:companies) {
            if(element.getEmpployeesNumber() == empployeesNumber){
                element.setcompanyName(company.getcompanyName());
                element.setEmployees(company.getEmployees());
                company=element;
            }
        }
        return company;
    }

    public Company addNewCompany(Company company){
        long empployeesNumber = companies.get(companies.size() - 1).getEmpployeesNumber()+1;
        company.setEmpployeesNumber(empployeesNumber);
        companies.add(company);
        return company;
    }

    public Company deleteCompany(long empployeesNumber){
        Company company = new Company();
        for (Company element:companies) {
            if(element.getEmpployeesNumber() == empployeesNumber){
                company=element;
                break;
            }
        }
        companies.remove(company);
        return company;
    }
}
