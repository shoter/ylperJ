package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.RepositoryBase;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import java.util.Date;
import java.util.List;

public class CarRepositoryImpl extends RepositoryBase implements CarRepository {

    public CarRepositoryImpl(Session session) {
        super(session);
    }


    public long getBookingsCountForCar(long carId) {
        Query query =session.createQuery("Select count(booking) from Booking booking where booking.car.id=:carId");
        query.setParameter("carId", carId);

        return (long)(Long) query.uniqueResult();
    }

    public boolean exist(long carId) {
        Car car = session.get(Car.class, carId);
        return car != null;
    }

    public Car getCar(long carId) {
        return session.get(Car.class, carId);
    }

    public void remove(long carId) {
        session.beginTransaction();
        session.remove(session.load(Car.class, carId));
        session.flush();
        session.getTransaction().commit();
    }

    public CarLocationHistory insertNewPosition(long carId, Point point) {
        CarLocationHistoryId id = new CarLocationHistoryId();
        id.setDateTime(new Date());
        id.setCarId(carId);

        CarLocationHistory location = new CarLocationHistory();
        location.setLocation(point);
        location.setCarLocationHistoryPK(id);

        session.beginTransaction();
        session.save(location);
        session.getTransaction().commit();

        return location;
    }

    public CarLocationHistory getLastCarHistory(long carId) {
        Query query = session.
                createQuery("FROM CarLocationHistory location " +
                        "WHERE location.carLocationHistoryPK.carId=:carId" +
                        " ORDER BY location.carLocationHistoryPK.dateTime DESC");
        query.setParameter("carId", carId);
        query.setMaxResults(1);
        query.setFirstResult(0);

        List<CarLocationHistory> locations = query.list();

        if(locations.size() == 0)
            return null;

        return locations.get(0);
    }

    public Car getFullCar(long carId) {
        Query query = session
                .createQuery("Select car FROM Car car " +
                        "JOIN FETCH car.carModel model " +
                        "JOIN FETCH model.designerCompany " +
                        "JOIN FETCH model.engine engine " +
                        "JOIN FETCH engine.fuelType " +
                        "JOIN FETCH model.luxuryCategory " +
                        "LEFT JOIN FETCH car.carFeatures " +
                        "WHERE car.id=:carId");
        query.setParameter("carId", carId);

       return (Car)query.getSingleResult();
    }

    public List<Car> getCars() {
        return session.createQuery("from Car").list();
    }
}
