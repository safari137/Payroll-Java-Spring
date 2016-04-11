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

        currentSession.beginTransaction();
        Criteria criteria = currentSession.createCriteria(type);
        List<T> itemList = criteria.list();
        currentSession.getTransaction().commit();

        return itemList;
    }

    @Override
    public T findById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();
        T item = currentSession.get(type, id);
        currentSession.getTransaction().commit();

        return item;
    }

    @Override
    public int insert(T item) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();

        int id = (int) currentSession.save(item);
        currentSession.flush();
        currentSession.getTransaction().commit();

        return id;
    }

    @Override
    public void update(T item) {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.saveOrUpdate(item);
        session.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        T item = this.findById(id);

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.delete(item);
        session.getTransaction().commit();
    }
}
