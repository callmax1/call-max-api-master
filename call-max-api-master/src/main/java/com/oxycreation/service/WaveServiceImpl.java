package com.oxycreation.service;


import com.oxycreation.dao.WaveDao;
import com.oxycreation.dto.WaveDto;
import com.oxycreation.model.Lob;
import com.oxycreation.model.Question;
import com.oxycreation.model.Vertical;
import com.oxycreation.model.Wave;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("waveService")
public class WaveServiceImpl implements WaveService {

    @Autowired
    public WaveDao waveDao;

    @Override
    @Transactional
    public Long add(WaveDto waveDto,Long userId) {
            Wave wave = new Wave();
            wave.setLobId(waveDto.getLobId());
            wave.setName(waveDto.getName());
            wave.setNewHireOrientation(waveDto.getNewHireOrientation());
            wave.setOnBoarding(waveDto.getOnBoarding());
            wave.setProductSpecificTraining(waveDto.getProductSpecificTraining());
            wave.setNumberOfCandidate(waveDto.getNumberOfCandidate());
            wave.setComment(waveDto.getComment());
            wave.setCreatedBy(userId);
            wave.setUpdatedBy(userId);
            wave.setCreatedAt(new Date());
            wave.setUpdatedAt(new Date());
            wave.setStatus(1);
         return  waveDao.add(wave);
    }

    @Override
    @Transactional
    public void update(WaveDto waveDto, Long id, Long userId) {
        try {
            Wave wave = waveDao.getById(id);
            wave.setName(waveDto.getName());
            wave.setLobId(waveDto.getLobId());
            wave.setName(waveDto.getName());
            wave.setNewHireOrientation(waveDto.getNewHireOrientation());
            wave.setOnBoarding(waveDto.getOnBoarding());
            wave.setProductSpecificTraining(waveDto.getProductSpecificTraining());
            wave.setNumberOfCandidate(waveDto.getNumberOfCandidate());
            wave.setComment(waveDto.getComment());
            wave.setUpdatedBy(userId);
            wave.setUpdatedAt(new Date());
            waveDao.update(wave);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public Wave getById(Long id) {
        Wave data = waveDao.getById(id);
        Wave wave = new Wave();
        wave.setId(data.getId());
        wave.setName(data.getName());
        wave.setLobId(data.getLobId());

        Lob l = new Lob();
        l.setId(data.getLob().getId());
        l.setName(data.getLob().getName());
        l.setCompanyId(data.getLob().getCompany().getId());
        l.setCompany(data.getLob().getCompany());
        wave.setLob(l);

        wave.setName(data.getName());
        wave.setNewHireOrientation(data.getNewHireOrientation());
        wave.setOnBoarding(data.getOnBoarding());
        wave.setProductSpecificTraining(data.getProductSpecificTraining());
        wave.setNumberOfCandidate(data.getNumberOfCandidate());
        wave.setComment(data.getComment());
        wave.setCreatedBy(data.getCreatedBy());
        wave.setUpdatedBy(data.getUpdatedBy());
        wave.setCreatedAt(data.getCreatedAt());
        wave.setUpdatedAt(data.getUpdatedAt());
        wave.setStatus(data.getStatus());

        return wave;
    }

    @Override
    @Transactional
    public Wave getByName(String name) {
        return waveDao.getByName(name).orElse(null);
    }

    @Override
    @Transactional
    public List<Wave> findByWaveName(String name) {
        List<Wave> waveList = new ArrayList<>();
      try{
          List<Wave> waves = waveDao.findByWaveName(name);
          waves.stream().forEach(data -> {
              Wave wave = new Wave();
              wave.setId(data.getId());
              wave.setName(data.getName());
              wave.setLobId(data.getLobId());

              Lob l = new Lob();
              l.setId(data.getLob().getId());
              l.setName(data.getLob().getName());
              l.setCompanyId(data.getLob().getCompany().getId());
              l.setCompany(data.getLob().getCompany());
              wave.setLob(l);

              wave.setName(data.getName());
              wave.setNewHireOrientation(data.getNewHireOrientation());
              wave.setOnBoarding(data.getOnBoarding());
              wave.setProductSpecificTraining(data.getProductSpecificTraining());
              wave.setNumberOfCandidate(data.getNumberOfCandidate());
              wave.setComment(data.getComment());
              wave.setCreatedBy(data.getCreatedBy());
              wave.setUpdatedBy(data.getUpdatedBy());
              wave.setCreatedAt(data.getCreatedAt());
              wave.setUpdatedAt(data.getUpdatedAt());
              wave.setStatus(data.getStatus());
              waveList.add(wave);
          });

      }catch (Exception e){
          e.printStackTrace();
      }
        return waveList;
    }

    @Override
    @Transactional
    public Pagination waves(Page page, String name, String propertyName, String sortOrder) {
            List<Wave> result = new ArrayList<>();
            int count = 0;
            try {
                List<Wave> waves = waveDao.waves(page, name,propertyName,sortOrder);
                waves.stream().forEach(data -> {
                    Wave wave = new Wave();
                    wave.setId(data.getId());
                    wave.setName(data.getName());
                    wave.setLobId(data.getLobId());

                    Lob l = new Lob();
                    l.setId(data.getLob().getId());
                    l.setName(data.getLob().getName());
                    l.setCompanyId(data.getLob().getCompany().getId());
                    l.setCompany(data.getLob().getCompany());
                    wave.setLob(l);

                    wave.setNewHireOrientation(data.getNewHireOrientation());
                    wave.setOnBoarding(data.getOnBoarding());
                    wave.setProductSpecificTraining(data.getProductSpecificTraining());
                    wave.setNumberOfCandidate(data.getNumberOfCandidate());
                    wave.setComment(data.getComment());
                    wave.setCreatedBy(data.getCreatedBy());
                    wave.setUpdatedBy(data.getUpdatedBy());
                    wave.setCreatedAt(data.getCreatedAt());
                    wave.setUpdatedAt(data.getUpdatedAt());
                    wave.setStatus(data.getStatus());
                    result.add(wave);
                });
                 count = waveDao.count(name);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new Pagination(result,count);
    }
}
