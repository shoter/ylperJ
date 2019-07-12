package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Repository;
import com.shoter.ylper.core.Results.MethodResult;
import org.locationtech.jts.geom.Point;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends Repository<Booking> {
    boolean exists(long bookingId);
    Booking get(long bookingId);

    List<Booking> getBookingsForUser(long userId);
    boolean bookingExistsInGivenTime(long carId, Date time);
    boolean bookingExistsInGivenTime(long carId, Date startTime, Date endTime);

    List<FindCarResult> findProperCar(Date startTime, Date endTime, Integer carLuxuryCategoryId, List<Integer> carFeatureIds, Point searchLocation);
}
