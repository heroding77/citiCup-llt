package com.dzc.llt.Service.Impl;

import com.dzc.llt.Pojo.Company;
import com.dzc.llt.Pojo.User;
import com.dzc.llt.Repository.CompanyRepository;
import com.dzc.llt.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:dzc
 * @date 2021-01-04 11:15
 */

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company findCompany(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public boolean existsCompany(String name) {
        return companyRepository.existsByName(name);
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }
}
