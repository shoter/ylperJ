package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Results.MethodResult;
import org.locationtech.jts.geom.Point;

import java.util.Date;

public interface BookingService {
    MethodResult canAdd(Booking booking);
    void add(Booking booking);
    Booking getBooking(long id);

    MethodResult canRemove(long bookingId);
    void remove(long bookingId);

    MethodResult canUpdateDropInfo(long bookingId, Date time, Point point);
    void updateDropInfo(long bookingId, Date time, Point point);

    boolean bookingExistsInGivenTimeForCar(long carId, Date time);
    boolean bookingExistsInGivenTimeForCar(long carId, Date startTime, Date endTime);
}
