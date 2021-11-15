package com.oxycreation.dao;

import com.oxycreation.model.Vertical;
import com.oxycreation.util.Page;

import java.util.List;
import java.util.Optional;

public interface VerticalDao {
    public Long add(Vertical vertical);
    public void update(Vertical vertical);
    public  Vertical getById(Long id);
    public Optional<Vertical> getByName(String name);
    public List<Vertical> findByVerticalName(String name);
    public List<Vertical> verticals(Page page, String name);
    public int count(String name);
}
