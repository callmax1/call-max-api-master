package com.oxycreation.dao;

import com.oxycreation.model.Company;
import com.oxycreation.util.Page;

import java.util.List;
import java.util.Optional;

public interface CompanyDao {
     Long add(Company company);
     void update(Company company);
      Company getById(Long id);
     Optional<Company> getByName(String name);
     List<Company> findByCompanyName(String name);
     List<Company> companies(Page page, String name,String propertyName, String sortOrder);
     int count(String name);
}
