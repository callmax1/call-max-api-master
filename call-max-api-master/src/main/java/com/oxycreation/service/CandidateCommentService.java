package com.oxycreation.service;

import com.oxycreation.dto.CandidateCommentDto;

import com.oxycreation.model.CandidateComment;

import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;

import java.util.List;

public interface CandidateCommentService {
    Long add(CandidateCommentDto candidateCommentDto, Long userId);
    CandidateComment getById(Long id);
    List<CandidateComment> getByCandidateId(Long candidateId);
    Pagination candidates(Page page, String email, String propertyName, String sortOrder);
}
