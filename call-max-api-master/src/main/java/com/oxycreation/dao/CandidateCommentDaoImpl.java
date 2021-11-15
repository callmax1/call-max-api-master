package com.oxycreation.dao;


import com.oxycreation.model.CandidateComment;
import com.oxycreation.util.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class CandidateCommentDaoImpl implements CandidateCommentDao{

    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public Long add(CandidateComment candidateComment) {
         Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(candidateComment);
    }

    @Override
    public CandidateComment getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CandidateComment query = session.get(CandidateComment.class, id);
        return query;
    }

    @Override
    public List<CandidateComment> getByCandidateId(Long candidateId) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(CandidateComment.class);
        try{
            dtc.add(Restrictions.eq("candidateId",candidateId));
            return dtc.getExecutableCriteria(session).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<CandidateComment> candidates(Page page, String comment, String propertyName, String sortOrder) {
        Session session = sessionFactory.getCurrentSession();
        Query dtc = session.createQuery("FROM CandidateComment r where lower(r.comment) like lower(:comment) ORDER BY " + propertyName + " " + sortOrder);

        dtc.setParameter("comment", "%" + comment + "%");
        dtc.setFirstResult((page.getPageIndex() - 1) * page.getPageSize());
        dtc.setMaxResults(page.getPageSize());
        return dtc.list();
    }

    @Override
    public int count(String comment) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(CandidateComment.class);
        dtc.add(Restrictions.like("comment", comment, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list().size();
    }
}
