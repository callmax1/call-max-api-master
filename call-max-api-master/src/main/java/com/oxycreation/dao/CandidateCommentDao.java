package com.oxycreation.dao;


import com.oxycreation.model.CandidateComment;
import com.oxycreation.util.Page;
import java.util.List;


public interface CandidateCommentDao {
     Long add(CandidateComment candidateComment);
     CandidateComment getById(Long id);
     List<CandidateComment> getByCandidateId(Long candidateId);
     List<CandidateComment> candidates(Page page, String comment, String propertyName, String sortOrder);
     int count(String comment);
}
