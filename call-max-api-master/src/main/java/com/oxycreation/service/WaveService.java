package com.oxycreation.service;

import com.oxycreation.dto.WaveDto;
import com.oxycreation.model.Wave;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;

import java.util.List;

public interface WaveService {
    Long add(WaveDto waveDto, Long userId);
    void update(WaveDto waveDto, Long id, Long userId);
    Wave getById(Long id);
    Wave getByName(String name);
    List<Wave> findByWaveName(String name);
    Pagination waves(Page page, String name, String propertyName, String sortOrder);
}
