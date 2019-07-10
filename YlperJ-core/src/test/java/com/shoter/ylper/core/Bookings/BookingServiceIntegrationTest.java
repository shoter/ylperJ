package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.Cars.CarRepositoryImpl;
import com.shoter.ylper.core.IntegrationTest;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.Users.User;
import com.shoter.ylper.core.Users.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

public class BookingServiceIntegrationTest extends IntegrationTest {

    private final long testUserId = 1;
    private final long testCarId = 1;

    private Booking correctBooking;
    private BookingService bookingService;

    @Override
    @BeforeEach
    public void beforeEachTest() {
        super.beforeEachTest();

        this.bookingService = new BookingServiceImpl(
                new BookingRepositoryImpl(session),
                new CarRepositoryImpl(session),
                new UserRepositoryImpl(session)
        );

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.DAY_OF_MONTH, 3);

        this.correctBooking = new Booking();
        correctBooking.setCar(session.load(Car.class, testCarId));
        correctBooking.setUser(session.load(User.class, testUserId));
        correctBooking.setStartDateTime(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 4);
        correctBooking.setEndDateTime(cal.getTime());
        correctBooking.setPickupPosition(geometryFactory.createPoint(new Coordinate(1,1)));
        correctBooking.setDropPosition(geometryFactory.createPoint(new Coordinate(1,1)));
    }

    @Test
    public void add_shouldAdd_correctBooking()
    {
        // smoke test
        bookingService.add(correctBooking);
    }

    @Test
    public void canAdd_shouldReturnSuccess_forCorrectBooking()
    {
        MethodResult result = bookingService.canAdd(correctBooking);
        assertTrue(result.isSuccess());
    }

    @Test
    public void canAdd_shouldReturnFailure_whenUserNotExist()
    {
        correctBooking.setUser(session.load(User.class, (long)-12345));
        MethodResult result = bookingService.canAdd(correctBooking);
        assertTrue(result.hasError(BookingErrors.userNotExist));
    }

    @Test
    public void canAdd_shouldReturnFailure_whenCarNotExist()
    {
        correctBooking.setCar(session.load(Car.class, (long)-12345));
        MethodResult result = bookingService.canAdd(correctBooking);
        assertTrue(result.hasError(BookingErrors.carNotExist));
    }

    @Test
    public void canAdd_shouldReturnFailure_whenEndTimeBeforeStartTime()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1999);
        correctBooking.setEndDateTime(cal.getTime());
        MethodResult result = bookingService.canAdd(correctBooking);
        assertTrue(result.hasError(BookingErrors.dropTimeBeforeStartTime));
    }

    @Test
    public void canUpdateDropInfo_shouldReturnFailure_whenBookingDoesNotExist()
    {
        MethodResult result = bookingService.canUpdateDropInfo(-123, new Date(), geometryFactory.createPoint(new Coordinate(1,1)));
        assertTrue(result.hasError(BookingErrors.bookingNotExist));
    }
}
