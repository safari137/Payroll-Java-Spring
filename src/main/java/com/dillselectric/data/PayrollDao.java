package com.dillselectric.data;

import com.dillselectric.contracts.Repository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PayrollDao<T> implements Repository<T> {

    private SessionFactory sessionFactory = new PayrollSessionFactory().buildSessionFactory();
    private final Class<T> type;

    public PayrollDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<T> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();

        Criteria criteria = currentSession.createCriteria(type);

        return criteria.list();
    }

    @Override
    public T findById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.get(type, id);
    }

    @Override
    public void insert(T item) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(item);
    }

    @Override
    public void update(T item) {
        sessionFactory.getCurrentSession().saveOrUpdate(item);
    }

    @Override
    public void delete(int id) {
        T item = this.findById(id);

        sessionFactory.getCurrentSession().delete(item);
    }
}
