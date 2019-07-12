package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Demands.Demand;
import com.shoter.ylper.core.RepositoryBase;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.locationtech.jts.geom.Point;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.List;

public class BookingRepositoryImpl extends RepositoryBase<Booking> implements BookingRepository {
    public BookingRepositoryImpl(Session session) {
        super(session);
    }

    public boolean exists(long bookingId) {
        Query query = session.createQuery("select 1 from Booking booking where booking.id=:bookingId");
        query.setParameter("bookingId", bookingId);

        return query.uniqueResult() != null;
    }

    public Booking get(long bookingId) {
        return session.get(Booking.class, bookingId);
    }

    public List<Booking> getBookingsForUser(long userId) {
        Query query = session
                .createQuery("Select booking from Booking booking " +
                        "WHERE booking.user.id=:userId", Booking.class);
        query.setParameter("userId", userId);

        return query.list();
    }

    public boolean bookingExistsInGivenTime(long carId, Date time) {
        Query query = session.createQuery("SELECT 1 FROM Booking booking " +
                "WHERE booking.car.id=:carId " +
                "AND :time BETWEEN startDateTime AND endDateTime");

        query.setParameter("carId", carId);
        query.setParameter("time", time);
        query.setMaxResults(1);

        return query.uniqueResult() != null;
    }

    public boolean bookingExistsInGivenTime(long carId, Date startTime, Date endTime) {
        Query query = session.createQuery("SELECT 1 FROM Booking booking " +
                "WHERE booking.car.id=:carId " +
                "AND (booking.startDateTime BETWEEN :startTime AND :endTime " +
                "OR booking.endDateTime BETWEEN :startTime AND :endTime )");

        query.setParameter("carId", carId);
        query.setParameter("startTime", startTime);
        query.setParameter("endTime", endTime);
        query.setMaxResults(1);

        return query.uniqueResult() != null;
    }


    public List<FindCarResult> findProperCar(Date startTime, Date endTime, Integer carLuxuryCategoryId, List<Integer> carFeatureIds, Point searchLocation) {
        EntityManager em = session.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = em.
                createNamedStoredProcedureQuery("Find_Car_For_Booking");

        StringBuilder featureSB = new StringBuilder();

        for(int i = 0;i < carFeatureIds.size();)
        {
            featureSB.append('(');
            featureSB.append(carFeatureIds.get(i));
            featureSB.append(')');
            ++i;
            if(i != carFeatureIds.size())
                featureSB.append(',');
        }

        query.setParameter("p_start_time", startTime);
        query.setParameter("p_end_time", endTime);
        query.setParameter("p_luxury_category_id", carLuxuryCategoryId);

        if(carFeatureIds.isEmpty() == false)
            query.setParameter("p_car_features", featureSB.toString());

        query.setParameter("p_point", searchLocation);

        return (List<FindCarResult>) query.getResultList();
    }
}
