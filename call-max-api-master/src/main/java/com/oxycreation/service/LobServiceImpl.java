package com.oxycreation.service;


import com.oxycreation.dao.LobDao;
import com.oxycreation.dto.LobDto;
import com.oxycreation.model.Lob;
import com.oxycreation.model.Vertical;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service("lobService")
public class LobServiceImpl implements LobService {

    @Autowired
    public LobDao lobDao;

    @Override
    @Transactional
    public Long add(LobDto lobDto,Long userId) {
            Lob lob = new Lob();
            lob.setCompanyId(lobDto.getCompanyId());
            lob.setVerticalId(lobDto.getVerticalId());
            lob.setName(lobDto.getName());
            lob.setPitch(lobDto.getPitch());
            lob.setClientObservedHoliday(lobDto.getClientObservedHoliday());
            lob.setHoursOfOperation(lobDto.getHoursOfOperation());
            lob.setTrainingDuration(lobDto.getTrainingDuration());
            lob.setMissedInTrainingTermination(lobDto.getMissedInTrainingTermination());
            lob.setPayRateDuringTraining(lobDto.getPayRateDuringTraining());
            lob.setPayRateDuringProduction(lobDto.getPayRateDuringProduction());
            lob.setBackgroundCheck(lobDto.getBackgroundCheck());
            lob.setDrugScreening(lobDto.getDrugScreening());
            lob.setMissedInTraining(lobDto.getMissedInTraining());

            lob.setCreatedBy(userId);
            lob.setUpdatedBy(userId);
            lob.setCreatedAt(new Date());
            lob.setUpdatedAt(new Date());
            lob.setStatus(1);
         return  lobDao.add(lob);
    }

    @Override
    @Transactional
    public void update(LobDto lobDto, Long id, Long userId) {
        try {
            Lob lob = lobDao.getById(id);
            lob.setCompanyId(lobDto.getCompanyId());
            lob.setVerticalId(lobDto.getVerticalId());
            lob.setName(lobDto.getName());
            lob.setName(lobDto.getName());
            lob.setPitch(lobDto.getPitch());
            lob.setClientObservedHoliday(lobDto.getClientObservedHoliday());
            lob.setHoursOfOperation(lobDto.getHoursOfOperation());
            lob.setTrainingDuration(lobDto.getTrainingDuration());
            lob.setMissedInTrainingTermination(lobDto.getMissedInTrainingTermination());
            lob.setPayRateDuringTraining(lobDto.getPayRateDuringTraining());
            lob.setPayRateDuringProduction(lobDto.getPayRateDuringProduction());
            lob.setBackgroundCheck(lobDto.getBackgroundCheck());
            lob.setDrugScreening(lobDto.getDrugScreening());
            lob.setMissedInTraining(lobDto.getMissedInTraining());
            lob.setUpdatedBy(userId);
            lob.setUpdatedAt(new Date());
            lobDao.update(lob);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public Lob getById(Long id) {
        Lob x = lobDao.getById(id);
        Lob lob = new Lob();
        lob.setId(x.getId());
        lob.setName(x.getName());
        lob.setCompanyId(x.getCompanyId());
        lob.setCompany(x.getCompany());
        lob.setVerticalId(x.getVerticalId());
        Vertical v = new Vertical();
        v.setId(x.getVertical().getId());
        v.setName(x.getVertical().getName());
        v.setQuestions(x.getVertical().getQuestions().stream().collect(Collectors.toList()));
        lob.setVertical(v);
        lob.setPitch(x.getPitch());
        lob.setClientObservedHoliday(x.getClientObservedHoliday());
        lob.setHoursOfOperation(x.getHoursOfOperation());
        lob.setTrainingDuration(x.getTrainingDuration());
        lob.setMissedInTrainingTermination(x.getMissedInTrainingTermination());
        lob.setPayRateDuringTraining(x.getPayRateDuringTraining());
        lob.setPayRateDuringProduction(x.getPayRateDuringProduction());
        lob.setBackgroundCheck(x.getBackgroundCheck());
        lob.setDrugScreening(x.getDrugScreening());
        lob.setMissedInTraining(x.getMissedInTraining());
        lob.setCreatedBy(x.getCreatedBy());
        lob.setUpdatedBy(x.getUpdatedBy());
        lob.setCreatedAt(x.getCreatedAt());
        lob.setUpdatedAt(x.getUpdatedAt());
        lob.setStatus(x.getStatus());
        return lob;
    }

    @Override
    @Transactional
    public Lob getByName(String name) {
        return lobDao.getByName(name).orElse(null);
    }

    @Override
    @Transactional
    public List<Lob> findByLobName(String name) {
        List<Lob> result = new ArrayList<>();
        try {
            List<Lob> lobs = lobDao.findByName(name);
            lobs.stream().forEach(x -> {
                Lob lob = new Lob();
                lob.setId(x.getId());
                lob.setName(x.getName());
                lob.setCompanyId(x.getCompanyId());
                lob.setCompany(x.getCompany());
                lob.setVerticalId(x.getVerticalId());
                Vertical v = new Vertical();
                v.setId(x.getVertical().getId());
                v.setName(x.getVertical().getName());
                v.setQuestions(x.getVertical().getQuestions().stream().collect(Collectors.toList()));
                lob.setVertical(v);
                lob.setPitch(x.getPitch());
                lob.setClientObservedHoliday(x.getClientObservedHoliday());
                lob.setHoursOfOperation(x.getHoursOfOperation());
                lob.setTrainingDuration(x.getTrainingDuration());
                lob.setMissedInTrainingTermination(x.getMissedInTrainingTermination());
                lob.setPayRateDuringTraining(x.getPayRateDuringTraining());
                lob.setPayRateDuringProduction(x.getPayRateDuringProduction());
                lob.setBackgroundCheck(x.getBackgroundCheck());
                lob.setDrugScreening(x.getDrugScreening());
                lob.setMissedInTraining(x.getMissedInTraining());
                lob.setCreatedBy(x.getCreatedBy());
                lob.setUpdatedBy(x.getUpdatedBy());
                lob.setCreatedAt(x.getCreatedAt());
                lob.setUpdatedAt(x.getUpdatedAt());
                lob.setStatus(x.getStatus());
                result.add(lob);
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Transactional
    public List<Lob> findByCompanyId(Long companyId,String name) {
        List<Lob> result = new ArrayList<>();
        try {
            List<Lob> lobs = lobDao.findByCompanyId(companyId,name);
             lobs.stream().forEach(x -> {
                Lob lob = new Lob();
                lob.setId(x.getId());
                lob.setName(x.getName());
                lob.setCompanyId(x.getCompanyId());
                lob.setCompany(x.getCompany());
                lob.setVerticalId(x.getVerticalId());
                Vertical v = new Vertical();
                v.setId(x.getVertical().getId());
                v.setName(x.getVertical().getName());
                v.setQuestions(x.getVertical().getQuestions().stream().collect(Collectors.toList()));
                lob.setVertical(v);
                lob.setPitch(x.getPitch());
                lob.setClientObservedHoliday(x.getClientObservedHoliday());
                lob.setHoursOfOperation(x.getHoursOfOperation());
                lob.setTrainingDuration(x.getTrainingDuration());
                lob.setMissedInTrainingTermination(x.getMissedInTrainingTermination());
                lob.setPayRateDuringTraining(x.getPayRateDuringTraining());
                lob.setPayRateDuringProduction(x.getPayRateDuringProduction());
                lob.setBackgroundCheck(x.getBackgroundCheck());
                lob.setDrugScreening(x.getDrugScreening());
                lob.setMissedInTraining(x.getMissedInTraining());
                lob.setCreatedBy(x.getCreatedBy());
                lob.setUpdatedBy(x.getUpdatedBy());
                lob.setCreatedAt(x.getCreatedAt());
                lob.setUpdatedAt(x.getUpdatedAt());
                lob.setStatus(x.getStatus());
                result.add(lob);
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    @Transactional
    public Pagination lobs(Page page, String name, String propertyName, String sortOrder) {

        List<Lob> result = new ArrayList<>();
        int count = 0;
        try {
            List<Lob> lobs = lobDao.lobs(page, name, propertyName,  sortOrder);;
            lobs.stream().forEach(x -> {
                Lob lob = new Lob();
                lob.setId(x.getId());
                lob.setName(x.getName());
                lob.setCompanyId(x.getCompanyId());
                lob.setCompany(x.getCompany());
                lob.setVerticalId(x.getVerticalId());
                Vertical v = new Vertical();
                v.setId(x.getVertical().getId());
                v.setName(x.getVertical().getName());
                v.setQuestions(x.getVertical().getQuestions().stream().collect(Collectors.toList()));
                lob.setVertical(v);
                lob.setPitch(x.getPitch());
                lob.setClientObservedHoliday(x.getClientObservedHoliday());
                lob.setHoursOfOperation(x.getHoursOfOperation());
                lob.setTrainingDuration(x.getTrainingDuration());
                lob.setMissedInTrainingTermination(x.getMissedInTrainingTermination());
                lob.setPayRateDuringTraining(x.getPayRateDuringTraining());
                lob.setPayRateDuringProduction(x.getPayRateDuringProduction());
                lob.setBackgroundCheck(x.getBackgroundCheck());
                lob.setDrugScreening(x.getDrugScreening());
                lob.setMissedInTraining(x.getMissedInTraining());
                lob.setCreatedBy(x.getCreatedBy());
                lob.setUpdatedBy(x.getUpdatedBy());
                lob.setCreatedAt(x.getCreatedAt());
                lob.setUpdatedAt(x.getUpdatedAt());
                lob.setStatus(x.getStatus());
                result.add(lob);
            });
            count = lobDao.count(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Pagination(result,count);
    }

}
