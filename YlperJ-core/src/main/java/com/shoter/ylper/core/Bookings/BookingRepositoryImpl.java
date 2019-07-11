package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Demands.Demand;
import com.shoter.ylper.core.RepositoryBase;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

        return query.uniqueResult() != null;
    }
}
