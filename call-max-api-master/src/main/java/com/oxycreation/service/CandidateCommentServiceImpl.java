package com.oxycreation.service;

import com.oxycreation.dao.CandidateCommentDao;

import com.oxycreation.dto.CandidateCommentDto;
import com.oxycreation.model.CandidateComment;
import com.oxycreation.model.User;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("candidateCommentService")
public class CandidateCommentServiceImpl implements CandidateCommentService{

    @Autowired
    public CandidateCommentDao candidateCommentDao;

    @Override
    @Transactional
    public Long add(CandidateCommentDto candidateCommentDto, Long userId) {
        CandidateComment candidateComment = new CandidateComment();
        candidateComment.setUserId(userId);
        candidateComment.setCandidateId(candidateCommentDto.getCandidateId());
        candidateComment.setCommentDate(new Date());
        candidateComment.setComment(candidateCommentDto.getComment());
        candidateComment.setCreatedBy(userId);
        candidateComment.setUpdatedBy(userId);
        candidateComment.setCreatedAt(new Date());
        candidateComment.setUpdatedAt(new Date());
        candidateComment.setStatus(1);
        return  candidateCommentDao.add(candidateComment);
    }

    @Override
    @Transactional
    public CandidateComment getById(Long id) {
        return candidateCommentDao.getById(id);
    }

    @Override
    @Transactional
    public List<CandidateComment> getByCandidateId(Long candidateId) {
        List<CandidateComment> result = new ArrayList<>();
        try{
            List<CandidateComment> candidateComments = candidateCommentDao.getByCandidateId(candidateId);

            candidateComments.stream().forEach(x -> {
                CandidateComment cm = new CandidateComment();
                cm.setId(x.getId());
                cm.setUserId(x.getUserId());
                User u = new User();
                u.setId(x.getUserId());
                u.setFirstName(x.getUser().getFirstName());
                u.setLastName(x.getUser().getLastName());
                u.setProfileImage(x.getUser().getProfileImage());
                cm.setUser(u);
                cm.setCandidateId(x.getCandidateId());
                cm.setCommentDate(x.getCommentDate());
                cm.setComment(x.getComment());
                cm.setCreatedBy(x.getCreatedBy());
                cm.setUpdatedBy(x.getUpdatedBy());
                cm.setCreatedAt(x.getCreatedAt());
                cm.setUpdatedAt(x.getUpdatedAt());
                cm.setStatus(x.getStatus());
                result.add(cm);
            });


        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Transactional
    public Pagination candidates(Page page, String comment, String propertyName, String sortOrder) {
        List<CandidateComment> result = new ArrayList<>();
        int count = 0;
        try{
            List<CandidateComment> candidateComments = candidateCommentDao.candidates(page, comment, propertyName,  sortOrder);
            candidateComments.stream().forEach(x -> {
                CandidateComment cm = new CandidateComment();
                cm.setId(x.getId());
                cm.setUserId(x.getUserId());
                User u = new User();
                u.setId(x.getUserId());
                u.setFirstName(x.getUser().getFirstName());
                u.setLastName(x.getUser().getLastName());
                cm.setUser(u);
                cm.setCandidateId(x.getCandidateId());
                cm.setCommentDate(x.getCommentDate());
                cm.setComment(x.getComment());
                cm.setCreatedBy(x.getCreatedBy());
                cm.setUpdatedBy(x.getUpdatedBy());
                cm.setCreatedAt(x.getCreatedAt());
                cm.setUpdatedAt(x.getUpdatedAt());
                cm.setStatus(x.getStatus());
                result.add(cm);
            });
            count = candidateCommentDao.count(comment);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new Pagination(result,count);
    }
}
