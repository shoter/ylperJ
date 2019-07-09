package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.RepositoryBase;
import org.hibernate.Session;

public class BookingRepositoryImpl extends RepositoryBase<Booking> implements BookingRepository {
    public BookingRepositoryImpl(Session session) {
        super(session);
    }
}
