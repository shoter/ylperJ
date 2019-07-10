package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Cars.CarRepository;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.ServiceBase;
import com.shoter.ylper.core.Users.UserRepository;
import org.locationtech.jts.geom.Point;

import java.util.Date;

public class BookingServiceImpl extends ServiceBase<Booking> implements BookingService {

    private BookingRepository bookingRepository;
    private CarRepository carRepository;
    private UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, CarRepository carRepository, UserRepository userRepository)
    {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public MethodResult canAdd(Booking booking) {
        MethodResult result = new MethodResult();

        if(userRepository.userExists(booking.getUser().getId()) == false)
        {
            result.addError(BookingErrors.userNotExist);
        }

        if(carRepository.exist(booking.getCar().getId()))
        {
            result.addError(BookingErrors.carNotExist);
        }

        if(booking.getStartDateTime() != null && booking.getEndDateTime() != null
            && booking.getEndDateTime().before(booking.getStartDateTime()))
        {
            result.addError(BookingErrors.dropTimeBeforeStartTime);
        }

        return result;
    }

    public void add(Booking booking) {
        bookingRepository.add(booking);
    }

    public MethodResult canRemove(long bookingId) {
        if(bookingRepository.exists(bookingId) == false)
        {
            return new MethodResult(BookingErrors.bookingNotExist);
        }

        return new MethodResult();
    }

    public void remove(long bookingId) {
        Booking booking = bookingRepository.get(bookingId);
        bookingRepository.remove(booking);
    }

    public MethodResult canUpdateDropInfo(long bookingId, Date time, Point point) {

        if(bookingRepository.exists(bookingId) == false)
        {
            return new MethodResult(BookingErrors.bookingNotExist);
        }

        if(time == null)
        {
            return new MethodResult(BookingErrors.dropTimeCannotBeNull);
        }

        if(point == null)
        {
            return new MethodResult(BookingErrors.dropPointCannotBeNullt);
        }

        MethodResult result = new MethodResult();

        Booking booking = bookingRepository.get(bookingId);

        if(time.before(booking.getStartDateTime()))
        {
            return new MethodResult(BookingErrors.dropTimeBeforeStartTime);
        }

        return new MethodResult();
    }

    public void updateDropInfo(long bookingId, Date time, Point point) {
        Booking booking = bookingRepository.get(bookingId);

        booking.setEndDateTime(time);
        booking.setDropPosition(point);

        bookingRepository.update(booking);
    }
}
