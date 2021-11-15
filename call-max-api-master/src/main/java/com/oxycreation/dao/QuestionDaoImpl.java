package com.oxycreation.dao;

import com.oxycreation.model.Question;
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
public class QuestionDaoImpl implements QuestionDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(List<Question> question) {
         Session session = sessionFactory.getCurrentSession();
         session.saveOrUpdate(question);
    }

    @Override
    public Long add(Question question) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(question);
    }


    @Override
    public void update(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.update(question);
    }

    @Override
    public Question getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Question query = session.get(Question.class, id);
        return query;
    }

    @Override
    public Optional<Question> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Question.class);
        dtc.add(Restrictions.like("name", name, MatchMode.EXACT));
        Question question = (Question) dtc.getExecutableCriteria(session).uniqueResult();
        if (question != null) return Optional.of(question);
        return Optional.ofNullable(question);
    }

    @Override
    public List<Question> findByQuestionName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Question.class);
        dtc.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list();
    }

    @Override
    public List<Question> questions(Page page, String name) {
        Session session = sessionFactory.getCurrentSession();
        Query dtc = session.createQuery("FROM Question r where lower(r.name) like lower(:name)");
        dtc.setParameter("name", "%" + name + "%");
        dtc.setFirstResult((page.getPageIndex() - 1) * page.getPageSize());
        dtc.setMaxResults(page.getPageSize());
        return dtc.list();
    }

    @Override
    public int count(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Question.class);
        dtc.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list().size();
    }
}

