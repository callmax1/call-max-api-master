package com.oxycreation.service;

import com.oxycreation.dto.CandidateDto;
import com.oxycreation.model.Candidate;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;

import java.util.List;

public interface CandidateService {
    Long add(CandidateDto candidateDto, Long userId);
    void update(CandidateDto candidateDto, Long id, Long userId);
    Candidate getById(Long id);
    Candidate getByEmail(String email);
    List<Candidate> findByCandidateEmail(String email);
    Pagination candidates(Page page, String email, String propertyName, String sortOrder);
}
