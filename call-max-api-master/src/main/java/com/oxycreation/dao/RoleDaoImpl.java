package com.oxycreation.dao;

import com.oxycreation.model.Role;
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
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long addRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(role);
    }

    @Override
    public void updateRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
    }

    @Override
    public Role getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Role query = session.get(Role.class, id);
        return query;
    }

    @Override
    public Optional<Role> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Role.class);
        dtc.add(Restrictions.like("name", name, MatchMode.EXACT));
        Role role = (Role) dtc.getExecutableCriteria(session).uniqueResult();
        if (role != null) return Optional.of(role);
        return Optional.ofNullable(role);
    }

    @Override
    public List<Role> findByRoleName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Role.class);
        dtc.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list();
    }

    @Override
    public List<Role> roles(Page page, String name) {
        Session session = sessionFactory.getCurrentSession();
        Query dtc = session.createQuery("FROM Role r where lower(r.name) like lower(:name)");
        dtc.setParameter("name", "%" + name + "%");
        dtc.setFirstResult((page.getPageIndex() - 1) * page.getPageSize());
        dtc.setMaxResults(page.getPageSize());
        return dtc.list();
    }

    @Override
    public int roleCount(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(Role.class);
        dtc.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list().size();
    }
}

