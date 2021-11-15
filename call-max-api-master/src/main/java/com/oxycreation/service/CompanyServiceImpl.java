package com.oxycreation.service;


import com.oxycreation.dao.CompanyDao;
import com.oxycreation.dto.CompanyDto;
import com.oxycreation.model.Company;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    public CompanyDao companyDao;

    @Override
    @Transactional
    public Long add(CompanyDto companyDto,Long userId) {
            Company company = new Company();
            company.setName(companyDto.getName());
            company.setEmail(companyDto.getEmail());
            company.setPhoneNumber(companyDto.getPhoneNumber());
            company.setHeadQuarters(companyDto.getHeadQuarters());
            company.setCategories(companyDto.getCategories());
            company.setImageUrl(companyDto.getImageUrl());
            company.setAddress(companyDto.getAddress());
            company.setDescription(companyDto.getDescription());
            company.setCreatedBy(userId);
            company.setUpdatedBy(userId);
            company.setCreatedAt(new Date());
            company.setUpdatedAt(new Date());
            company.setStatus(1);
         return  companyDao.add(company);
    }

    @Override
    @Transactional
    public void update(CompanyDto companyDto, Long id, Long userId) {
        try {
            Company company = companyDao.getById(id);
            company.setName(companyDto.getName());
            company.setEmail(companyDto.getEmail());
            company.setPhoneNumber(companyDto.getPhoneNumber());
            company.setHeadQuarters(companyDto.getHeadQuarters());
            company.setCategories(companyDto.getCategories());
            company.setImageUrl(companyDto.getImageUrl());
            company.setAddress(companyDto.getAddress());
            company.setDescription(companyDto.getDescription());
            company.setUpdatedBy(userId);
            company.setUpdatedAt(new Date());
            companyDao.update(company);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public Company getById(Long id) {
        return companyDao.getById(id);
    }

    @Override
    @Transactional
    public Company getByName(String name) {
        return companyDao.getByName(name).orElse(null);
    }

    @Override
    @Transactional
    public List<Company> findByCompanyName(String name) {
        return companyDao.findByCompanyName(name);
    }



    @Transactional
    public Pagination companies(Page page, String name, String propertyName, String sortOrder) {
        List<Company> result = companyDao.companies(page, name, propertyName,  sortOrder);
        int count = companyDao.count(name);
        return new Pagination(result,count);
    }
}
