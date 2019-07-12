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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Test
    public void bookingExistsInGivenTimeForCar_shouldReturnTrue_whenBookingExist()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 16);
        cal.set(Calendar.MINUTE, 0);

        //car Id 1 should have booking at this time

        assertTrue(bookingService.bookingExistsInGivenTimeForCar(1, cal.getTime()));
    }

    @Test
    public void bookingExistsInGivenTimeForCar_shouldReturnFalse_whenNoBookingExist()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 18);
        cal.set(Calendar.MINUTE, 1);


        // no bookings for car 1
        assertFalse(bookingService.bookingExistsInGivenTimeForCar(1, cal.getTime()));
    }

    @Test
    public void bookingExistsInGivenTimeForCar_shouldReturnTrue_whenBookingIsInsideDateRange() throws ParseException {
        Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 01:00");
        Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 23:00");

        assertTrue(bookingService.bookingExistsInGivenTimeForCar(1, startTime, endTime));
    }

    @Test
    public void bookingExistsInGivenTimeForCar_shouldReturnTrue_whenDateRangeEndIsInsideBooking() throws ParseException {
        Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 01:00");
        Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 15:00");

        assertTrue(bookingService.bookingExistsInGivenTimeForCar(1, startTime, endTime));
    }

    @Test
    public void bookingExistsInGivenTimeForCar_shouldReturnTrue_whenDateRangeStartIsInsideBooking() throws ParseException {
        Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 16:00");
        Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 18:00");

        assertTrue(bookingService.bookingExistsInGivenTimeForCar(1, startTime, endTime));
    }

    @Test
    public void bookingExistsInGivenTimeForCar_shouldReturnFalse_whenDateRangeIsNotCollidingWithBooking() throws ParseException {
        Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 18:00");
        Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 20:00");

        assertFalse(bookingService.bookingExistsInGivenTimeForCar(1, startTime, endTime));
    }

    @Test
    public void canAdd_shouldReturnError_whenTimeIsAlreadyBooked() throws ParseException {
        Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 16:00");
        Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 18:00");

        this.correctBooking.setStartDateTime(startTime);
        this.correctBooking.setEndDateTime(endTime);
        this.correctBooking.setCar(session.load(Car.class,(long)1));

        assertTrue(bookingService.canAdd(this.correctBooking).hasError(BookingErrors.carIsAlreadyBookedInThisTime));
    }

 /*   @Test
    public void findProperCar_shouldReturn3Cars_forSpecificSetting() throws ParseException {
        // I do not like this method personally. It should create booking data inside so person that is trying to understand method

        Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 18:20");
        Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-07-01 ");


    }*/
}
