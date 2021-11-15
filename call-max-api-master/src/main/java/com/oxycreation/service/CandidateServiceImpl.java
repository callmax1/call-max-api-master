package com.oxycreation.service;


import com.oxycreation.dao.CandidateDao;
import com.oxycreation.dto.CandidateDto;


import com.oxycreation.model.Candidate;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("candidateService")
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    public CandidateDao candidateDao;

    @Override
    @Transactional
    public Long add(CandidateDto candidateDto, Long userId) {
        Candidate candidate = new Candidate();
        candidate.setFirstName(candidateDto.getFirstName());
        candidate.setLastName(candidateDto.getLastName());
        candidate.setPhoneNumber(candidateDto.getPhoneNumber());
        candidate.setGender(candidateDto.getGender());
        candidate.setEmail(candidateDto.getEmail());
        candidate.setStateId(candidateDto.getStateId());
        candidate.setCity(candidateDto.getCity());
        candidate.setAddress(candidateDto.getAddress());
        candidate.setWorkingExperience(candidateDto.getWorkingExperience());
        candidate.setCompanyName(candidateDto.getCompanyName());
        candidate.setQualification(candidateDto.getQualification());
        candidate.setUniversity(candidateDto.getUniversity());
        candidate.setResume(candidateDto.getResume());
        candidate.setResumeUrl(candidateDto.getResumeUrl());
        candidate.setSpeedTest(candidateDto.getSpeedTest());
        candidate.setSpeedTestUrl(candidateDto.getSpeedTestUrl());
        candidate.setSkillTest(candidateDto.getSkillTest());
        candidate.setSkillTestUrl(candidateDto.getSkillTestUrl());
        candidate.setSpeedTestUpload(candidateDto.getSpeedTestUpload());
        candidate.setSpeedTestDownload(candidateDto.getSpeedTestDownload());
        candidate.setSkillTestSpeed(candidateDto.getSkillTestSpeed());
        candidate.setSkillTestAccuracy(candidateDto.getSkillTestAccuracy());
        candidate.setDescription(candidateDto.getDescription());
        candidate.setUserId(candidateDto.getUserId());
        candidate.setAssignDate(candidateDto.getAssignDate());
        candidate.setWaveId(candidateDto.getWaveId());
        candidate.setCreatedBy(userId);
        candidate.setUpdatedBy(userId);
        candidate.setCreatedAt(new Date());
        candidate.setUpdatedAt(new Date());
        candidate.setStatus(1);
        return candidateDao.add(candidate);
    }

    @Override
    @Transactional
    public void update(CandidateDto candidateDto, Long id, Long userId) {
        try {
            Candidate candidate = candidateDao.getById(id);
            candidate.setFirstName(candidateDto.getFirstName());
            candidate.setLastName(candidateDto.getLastName());
            candidate.setPhoneNumber(candidateDto.getPhoneNumber());
            candidate.setGender(candidateDto.getGender());
            candidate.setEmail(candidateDto.getEmail());
            candidate.setStateId(candidateDto.getStateId());
            candidate.setCity(candidateDto.getCity());
            candidate.setAddress(candidateDto.getAddress());
            candidate.setWorkingExperience(candidateDto.getWorkingExperience());
            candidate.setCompanyName(candidateDto.getCompanyName());
            candidate.setQualification(candidateDto.getQualification());
            candidate.setUniversity(candidateDto.getUniversity());
            candidate.setResume(candidateDto.getResume());
            candidate.setResumeUrl(candidateDto.getResumeUrl());
            candidate.setSpeedTest(candidateDto.getSpeedTest());
            candidate.setSpeedTestUrl(candidateDto.getSpeedTestUrl());
            candidate.setSkillTest(candidateDto.getSkillTest());
            candidate.setSkillTestUrl(candidateDto.getSkillTestUrl());
            candidate.setSpeedTestUpload(candidateDto.getSpeedTestUpload());
            candidate.setSpeedTestDownload(candidateDto.getSpeedTestDownload());
            candidate.setSkillTestSpeed(candidateDto.getSkillTestSpeed());
            candidate.setSkillTestAccuracy(candidateDto.getSkillTestAccuracy());
            candidate.setUserId(candidateDto.getUserId());
            candidate.setAssignDate(candidateDto.getAssignDate());
            candidate.setWaveId(candidateDto.getWaveId());
            candidate.setUpdatedBy(userId);
            candidate.setUpdatedAt(new Date());
            candidateDao.update(candidate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public Candidate getById(Long id) {
        return candidateDao.getById(id);
    }

    @Override
    @Transactional
    public Candidate getByEmail(String email) {
        return candidateDao.getByEmail(email).orElse(null);
    }

    @Override
    @Transactional
    public List<Candidate> findByCandidateEmail(String email) {
        return candidateDao.findByCandidateEmail(email);
    }


    @Transactional
    public Pagination candidates(Page page, String email, String propertyName, String sortOrder) {
        List<Candidate> result = candidateDao.candidates(page, email, propertyName, sortOrder);
        int count = candidateDao.count(email);
        return new Pagination(result, count);
    }
}
