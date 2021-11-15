package com.oxycreation.service;

import com.oxycreation.dto.VerticalDto;
import com.oxycreation.model.Vertical;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;

import java.util.List;

public interface VerticalService {
    Long add(VerticalDto verticalDto,Long userId);
    void update(VerticalDto verticalDto, Long id, Long userId);
    Vertical getById(Long id);
    Vertical getByName(String name);
    List<Vertical> findByVerticalName(String name);
    Pagination verticals(Page page, String name);
}
