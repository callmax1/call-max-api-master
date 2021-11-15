package com.oxycreation.service;

import com.oxycreation.dto.StateDto;

import com.oxycreation.model.State;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;

import java.util.List;

public interface StateService {
    Long addState(StateDto stateDto, Long userId);
    State getByName(String name);
    void updateState(StateDto stateDto, Long id, Long userId);
    List<State> findByStateName(String name);
    State getById(Long id);
    Pagination states(Page page, String name, String propertyName, String sortOrder);
    void deleteState(Long id, Long userId);
}
