package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Results.MethodResult;
import com.vividsolutions.jts.geom.Point;

import java.util.Date;

public interface BookingService {
    MethodResult canAdd(Booking booking);
    void Add(Booking booking);

    MethodResult canUpdateDropInfo(long bookingId, Date time, Point point);
    MethodResult updateDropInfo(long bookingId, Date time, Point point);
}
