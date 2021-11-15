package com.oxycreation.dao;

import com.oxycreation.model.Lob;
import com.oxycreation.util.Page;

import java.util.List;
import java.util.Optional;

public interface LobDao {
     Long add(Lob lob);
     void update(Lob lob);
      Lob getById(Long id);
     Optional<Lob> getByName(String name);
     List<Lob> findByName(String name);
     List<Lob> findByCompanyId(Long companyId,String name);
     List<Lob> lobs(Page page, String name, String propertyName, String sortOrder);
     int count(String name);
}
