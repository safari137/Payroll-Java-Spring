package com.dillselectric.data;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class PayrollSessionFactory {
    private static final org.hibernate.SessionFactory sessionFactory = buildSessionFactory();

    public PayrollSessionFactory() {
    }

    public static org.hibernate.SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
}
