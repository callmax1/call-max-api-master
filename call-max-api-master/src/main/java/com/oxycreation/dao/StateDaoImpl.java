package com.oxycreation.dao;

import com.oxycreation.model.State;
import com.oxycreation.util.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class StateDaoImpl implements StateDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long addState(State state) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(state);
    }

    @Override
    public void updateState(State state) {
        Session session = sessionFactory.getCurrentSession();
        session.update(state);
    }

    @Override
    public Optional<State> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(State.class);
        dtc.add(Restrictions.like("name", name, MatchMode.EXACT));
        State state = (State) dtc.getExecutableCriteria(session).uniqueResult();
        if (state != null) return Optional.of(state);
        return Optional.ofNullable(state);
    }
    @Override
    public List<State> findByStateName(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(State.class);
        dtc.add(Restrictions.ilike("name",name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list();
    }


    @Override
    public State getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        State query = session.get(State.class, id);
        return query;
    }

    @Override
    public List<State> states(Page page, String name, String propertyName, String sortOrder) {
        Session session = sessionFactory.getCurrentSession();
        Query dtc = session.createQuery("FROM State s where s.name like :name  ORDER BY " + propertyName + " " + sortOrder );
        dtc.setParameter("name", "%" + name + "%");
        dtc.setFirstResult((page.getPageIndex() -1) * page.getPageSize());
        dtc.setMaxResults(page.getPageSize());
        return dtc.list();
    }

    @Override
    public int stateCount(String name) {
        Session session = sessionFactory.getCurrentSession();
        DetachedCriteria dtc = DetachedCriteria.forClass(State.class);
        dtc.add(Restrictions.like("name",name, MatchMode.ANYWHERE));
        return dtc.getExecutableCriteria(session).list().size();
    }

    @Override
    public void deleteState(Long id, Long userId, Date updateAt, Integer status) {
        Session session = sessionFactory.getCurrentSession();
        State state = session.load(State.class,id);
        state.setUpdatedBy(userId);
        state.setUpdatedAt(updateAt);
        state.setStatus(status);
        session.update(state);
    }
}
