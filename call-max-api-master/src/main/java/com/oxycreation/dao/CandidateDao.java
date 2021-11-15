package com.oxycreation.dao;

import com.oxycreation.model.Candidate;
import com.oxycreation.util.Page;

import java.util.List;
import java.util.Optional;

public interface CandidateDao {
     Long add(Candidate candidate);
     void update(Candidate candidate);
      Candidate getById(Long id);
     Optional<Candidate> getByEmail(String email);
     List<Candidate> findByCandidateEmail(String email);
     List<Candidate> candidates(Page page, String email, String propertyName, String sortOrder);
     int count(String email);
}
