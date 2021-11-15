package com.oxycreation.dao;

import com.oxycreation.model.Company;
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
public class CompanyDaoImpl implements CompanyDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Company company) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(company);
    }

    @Override
    public void update(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.update(company);
    }

    @Override
    public Company getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Company query = session.get(Company.class, id);
        return query;
    }

    @Override
    public Optional<Company> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Company.class);
        dtc.add(Restrictions.like("name", name, MatchMode.EXACT));
        Company company = (Company) dtc.getExecutableCriteria(session).uniqueResult();
        if (company != null) return Optional.of(company);
        return Optional.ofNullable(company);
    }

    @Override
    public List<Company> findByCompanyName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Company.class);
        dtc.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list();
    }

    @Override
    public List<Company> companies(Page page, String name,String propertyName, String sortOrder) {
        Session session = sessionFactory.getCurrentSession();
        Query dtc = session.createQuery("FROM Company r where lower(r.name) like lower(:name) ORDER BY " + propertyName + " " + sortOrder);

        dtc.setParameter("name", "%" + name + "%");
        dtc.setFirstResult((page.getPageIndex() - 1) * page.getPageSize());
        dtc.setMaxResults(page.getPageSize());
        return dtc.list();
    }

    @Override
    public int count(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Company.class);
        dtc.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list().size();
    }
}

