package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Repository;
import com.shoter.ylper.core.Results.MethodResult;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends Repository<Booking> {
    boolean exists(long bookingId);
    Booking get(long bookingId);

    List<Booking> getBookingsForUser(long userId);
    boolean bookingExistsInGivenTime(long carId, Date time);
    boolean bookingExistsInGivenTime(long carId, Date startTime, Date endTime);
}
