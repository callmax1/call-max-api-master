package com.oxycreation.service;

import com.oxycreation.dto.LobDto;
import com.oxycreation.model.Lob;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;

import java.util.List;

public interface LobService {
    Long add(LobDto lobDto,Long userId);
    void update(LobDto lobDto, Long id, Long userId);
    Lob getById(Long id);
    Lob getByName(String name);
    List<Lob> findByLobName(String name);
    List<Lob> findByCompanyId(Long companyId,String name);
    Pagination lobs(Page page, String name, String propertyName, String sortOrder);
}
