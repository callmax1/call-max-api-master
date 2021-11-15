package com.oxycreation.dao;

import com.oxycreation.model.Wave;
import com.oxycreation.util.Page;

import java.util.List;
import java.util.Optional;

public interface WaveDao {
    public Long add(Wave wave);
    public void update(Wave wave);
    public  Wave getById(Long id);
    public Optional<Wave> getByName(String name);
    public List<Wave> findByWaveName(String name);
    public List<Wave> waves(Page page, String name,String propertyName, String sortOrder);
    public int count(String name);
}
