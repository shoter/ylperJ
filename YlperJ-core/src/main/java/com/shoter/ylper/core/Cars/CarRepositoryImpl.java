package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.RepositoryBase;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CarRepositoryImpl extends RepositoryBase implements CarRepository {

    public CarRepositoryImpl(Session session) {
        super(session);
    }


    public long getBookingsCountForCar(long carId) {
        Query query =session.createQuery("Select count(*) from Booking booking where booking.carId=:carId");
        query.setParameter("carId", carId);

        return (long)(Long) query.uniqueResult();
    }

    public boolean exist(long carId) {
        return session.get(Car.class, carId) != null;
    }

    public Car getCar(long carId) {
        return session.get(Car.class, carId);
    }

    public void remove(long carId) {
         session.remove(session.load(Car.class, carId));
    }
}
