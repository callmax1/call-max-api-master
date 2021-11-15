package com.oxycreation.dao;

import com.oxycreation.model.State;
import com.oxycreation.util.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StateDao {
    public Long addState(State state);
    public void updateState(State state);
    public Optional<State> getByName(String name);
    public List<State> findByStateName(String name);

    public  State getById(Long id);
    public List<State> states(Page page, String name, String propertyName, String sortOrder);
    public int stateCount(String name);
    public  void deleteState(Long id, Long userId, Date updateAt, Integer status);
}
