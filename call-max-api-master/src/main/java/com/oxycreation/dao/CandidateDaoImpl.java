package com.oxycreation.dao;

import com.oxycreation.model.Candidate;
import com.oxycreation.util.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CandidateDaoImpl implements CandidateDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Candidate candidate) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(candidate);
    }

    @Override
    public void update(Candidate candidate) {
        Session session = sessionFactory.getCurrentSession();
        session.update(candidate);
    }

    @Override
    public Candidate getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Candidate query = session.get(Candidate.class, id);
        return query;
    }

    @Override
    public Optional<Candidate> getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Candidate.class);
        dtc.add(Restrictions.like("email", email, MatchMode.EXACT));
        Candidate candidate = (Candidate) dtc.getExecutableCriteria(session).uniqueResult();
        if (candidate != null) return Optional.of(candidate);
        return Optional.ofNullable(candidate);
    }

    @Override
    public List<Candidate> findByCandidateEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Candidate.class);
        dtc.add(Restrictions.ilike("email", email, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list();
    }

    @Override
    public List<Candidate> candidates(Page page, String email,String propertyName, String sortOrder) {
        Session session = sessionFactory.getCurrentSession();
        Query dtc = session.createQuery("FROM Candidate r where lower(r.email) like lower(:email) ORDER BY " + propertyName + " " + sortOrder);

        dtc.setParameter("email", "%" + email + "%");
        dtc.setFirstResult((page.getPageIndex() - 1) * page.getPageSize());
        dtc.setMaxResults(page.getPageSize());
        return dtc.list();
    }

    @Override
    public int count(String email) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Candidate.class);
        dtc.add(Restrictions.like("email", email, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list().size();
    }
}

