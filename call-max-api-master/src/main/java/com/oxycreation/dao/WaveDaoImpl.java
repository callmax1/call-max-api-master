package com.oxycreation.dao;

import com.oxycreation.model.Wave;
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
public class WaveDaoImpl implements WaveDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Wave wave) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(wave);
    }

    @Override
    public void update(Wave wave) {
        Session session = sessionFactory.getCurrentSession();
        session.update(wave);
    }

    @Override
    public Wave getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Wave query = session.get(Wave.class, id);
        return query;
    }

    @Override
    public Optional<Wave> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Wave.class);
        dtc.add(Restrictions.like("name", name, MatchMode.EXACT));
        Wave wave = (Wave) dtc.getExecutableCriteria(session).uniqueResult();
        if (wave != null) return Optional.of(wave);
        return Optional.ofNullable(wave);
    }

    @Override
    public List<Wave> findByWaveName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Wave.class);
        dtc.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list();
    }

    @Override
    public List<Wave> waves(Page page, String name,String propertyName, String sortOrder) {
        Session session = sessionFactory.getCurrentSession();
        Query dtc = session.createQuery("FROM Wave r where lower(r.name) like lower(:name) ORDER BY " + propertyName + " " + sortOrder);
        dtc.setParameter("name", "%" + name + "%");
        dtc.setFirstResult((page.getPageIndex() - 1) * page.getPageSize());
        dtc.setMaxResults(page.getPageSize());
        return dtc.list();
    }

    @Override
    public int count(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Wave.class);
        dtc.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list().size();
    }
}

