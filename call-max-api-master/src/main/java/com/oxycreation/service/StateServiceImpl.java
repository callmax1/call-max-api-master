package com.oxycreation.service;

import com.oxycreation.dao.StateDao;
import com.oxycreation.dto.StateDto;
import com.oxycreation.model.State;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service("stateService")
public class StateServiceImpl implements StateService{
    @Autowired
    private StateDao stateDao;

    @Override
    @Transactional
    public Long addState(StateDto stateDto, Long userId) {
        State state = new State();
        state.setName(stateDto.getName());
         state.setCode(stateDto.getCode());
        state.setDescription(stateDto.getDescription());
        state.setCreatedBy(userId);
        state.setUpdatedBy(userId);
        state.setCreatedAt(new Date());
        state.setUpdatedAt(new Date());
        state.setStatus(1);
        return stateDao.addState(state);
    }

    @Override
    @Transactional
    public void updateState(StateDto stateDto, Long id, Long userId) {
        try {
            State state = stateDao.getById(id);
            state.setName(stateDto.getName());
            state.setCode(stateDto.getCode());
            state.setDescription(stateDto.getDescription());
            state.setUpdatedBy(userId);
            state.setUpdatedAt(new Date());
            stateDao.updateState(state);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public State getByName(String name) {
        return stateDao.getByName(name).orElse(null);
    }

    @Override
    @Transactional
    public List<State> findByStateName(String name) {
        return stateDao.findByStateName(name);
    }


    @Override
    @Transactional
    public State getById(Long id) {
        return stateDao.getById(id);
    }

    @Override
    @Transactional
    public Pagination states(Page page, String name, String propertyName, String sortOrder) {
        List<State> result = stateDao.states(page, name,propertyName,sortOrder);
        int count = stateDao.stateCount(name);
        return new Pagination(result,count);
    }

    @Override
    @Transactional
    public void deleteState(Long id, Long userId) {
        try {
            stateDao.deleteState(id,userId,new Date(),0);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
