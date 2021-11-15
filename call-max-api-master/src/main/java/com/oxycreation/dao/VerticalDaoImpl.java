package com.oxycreation.dao;

import com.oxycreation.model.Vertical;
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
public class VerticalDaoImpl implements VerticalDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Vertical vertical) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(vertical);
    }

    @Override
    public void update(Vertical vertical) {
        Session session = sessionFactory.getCurrentSession();
        session.update(vertical);
    }

    @Override
    public Vertical getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Vertical query = session.get(Vertical.class, id);
        return query;
    }

    @Override
    public Optional<Vertical> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Vertical.class);
        dtc.add(Restrictions.like("name", name, MatchMode.EXACT));
        Vertical vertical = (Vertical) dtc.getExecutableCriteria(session).uniqueResult();
        if (vertical != null) return Optional.of(vertical);
        return Optional.ofNullable(vertical);
    }

    @Override
    public List<Vertical> findByVerticalName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Vertical.class);
        dtc.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list();
    }

    @Override
    public List<Vertical> verticals(Page page, String name) {
        Session session = sessionFactory.getCurrentSession();
        Query dtc = session.createQuery("FROM Vertical r where lower(r.name) like lower(:name)");
        dtc.setParameter("name", "%" + name + "%");
        dtc.setFirstResult((page.getPageIndex() - 1) * page.getPageSize());
        dtc.setMaxResults(page.getPageSize());
        return dtc.list();
    }

    @Override
    public int count(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Vertical.class);
        dtc.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list().size();
    }
}

