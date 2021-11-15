package com.oxycreation.dao;

import com.oxycreation.model.Lob;
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
import java.util.Optional;

@Repository
public class LobDaoImpl implements LobDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Lob lob) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(lob);
    }

    @Override
    public void update(Lob lob) {
        Session session = sessionFactory.getCurrentSession();
        session.update(lob);
    }

    @Override
    public Lob getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Lob query = session.get(Lob.class, id);
        return query;
    }

    @Override
    public Optional<Lob> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Lob.class);
        dtc.add(Restrictions.like("name", name, MatchMode.EXACT));
        Lob lob = (Lob) dtc.getExecutableCriteria(session).uniqueResult();
        if (lob != null) return Optional.of(lob);
        return Optional.ofNullable(lob);
    }

    @Override
    public List<Lob> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Lob.class);
        dtc.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list();
    }

    @Override
    public List<Lob> findByCompanyId(Long companyId,String name) {
         Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Lob.class);
        try{
            dtc.add(Restrictions.eq("companyId",companyId));
            dtc.add(Restrictions.like("name",name, MatchMode.ANYWHERE));
            return dtc.getExecutableCriteria(session).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Lob> lobs(Page page, String name,String propertyName, String sortOrder) {
        Session session = sessionFactory.getCurrentSession();
        Query dtc = session.createQuery("FROM Lob r where lower(r.name) like lower(:name) ORDER BY " + propertyName + " " + sortOrder);
        dtc.setParameter("name", "%" + name + "%");
        dtc.setFirstResult((page.getPageIndex() - 1) * page.getPageSize());
        dtc.setMaxResults(page.getPageSize());
        return dtc.list();
    }

    @Override
    public int count(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Lob.class);
        dtc.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list().size();
    }
}

