package com.oxycreation.service;

import com.oxycreation.dto.CompanyDto;
import com.oxycreation.model.Company;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;

import java.util.List;

public interface CompanyService {
    Long add(CompanyDto companyDto,Long userId);
    void update(CompanyDto companyDto, Long id, Long userId);
    Company getById(Long id);
    Company getByName(String name);
    List<Company> findByCompanyName(String name);
    Pagination companies(Page page, String name,String propertyName, String sortOrder);
}
