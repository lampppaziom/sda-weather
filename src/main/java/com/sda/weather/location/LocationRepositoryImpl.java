package com.sda.weather.location;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LocationRepositoryImpl implements LocationRepository {

    private final SessionFactory sessionFactory;

    public LocationRepositoryImpl(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    @Override
    public Location save(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(location);
        transaction.commit();
        session.close();

        return location;
    }
}
