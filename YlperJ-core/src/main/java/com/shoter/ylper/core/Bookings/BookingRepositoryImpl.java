package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.RepositoryBase;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
}
