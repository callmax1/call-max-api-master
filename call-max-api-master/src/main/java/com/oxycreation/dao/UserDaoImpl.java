package com.oxycreation.dao;

import com.oxycreation.model.User;
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
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }
    @Override
    public Optional<User> getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(User.class);
        dtc.add(Restrictions.like("email", email, MatchMode.EXACT));
        User user = (User) dtc.getExecutableCriteria(session).uniqueResult();
        if (user != null) return Optional.of(user);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findByAgentName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User u where u.roleId = 3 and (u.firstName like :firstName or u.lastName like :lastName)" );

        query.setParameter("firstName", "%" + name + "%");
        query.setParameter("lastName", "%" + name + "%");
        return query.list();
    }

    @Override
    public User getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User query = session.get(User.class, id);
        return query;
    }

    @Override
    public List<User> users(Page page,Long id, String email, String propertyName, String sortOrder) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User u where u.id != :id and u.email like :email ORDER BY "  + propertyName + " " + sortOrder );
        query.setParameter("id",id);
        query.setParameter("email", "%" + email + "%");
        query.setFirstResult((page.getPageIndex() - 1) * page.getPageSize());
        query.setMaxResults(page.getPageSize());
        return query.list();
    }

    @Override
    public int userCount(String email) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(User.class);
        dtc.add(Restrictions.like("email", email, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list().size();
    }

    @Override
    public List<User> findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(User.class);
        dtc.add(Restrictions.like("email",email, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list();
    }
}

