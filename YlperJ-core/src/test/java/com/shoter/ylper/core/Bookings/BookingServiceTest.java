package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.Cars.CarRepository;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.Users.User;
import com.shoter.ylper.core.Users.UserRepository;
import com.shoter.ylper.core.YlperTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.*;

public class BookingServiceTest extends YlperTest {
    private BookingService bookingService;

    private BookingRepository bookingRepositoryMock;
    private CarRepository carRepositoryMock;
    private UserRepository userRepositoryMock;

    private User userMock;
    private Car carMock;

    private Booking correctBooking;

    @BeforeEach
    @Override
    public void beforeEachTest()
    {
        super.beforeEachTest();
        this.bookingRepositoryMock = mock(BookingRepository.class);
        this.carRepositoryMock = mock(CarRepository.class);
        this.userRepositoryMock = mock(UserRepository.class);

        this.bookingService = new BookingServiceImpl(this.bookingRepositoryMock, this.carRepositoryMock, this.userRepositoryMock);

        this.userMock = mock(User.class);
        this.carMock = mock(Car.class);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.DAY_OF_MONTH, 3);

        // TODO: Remove if there will be no tests using that
        this.correctBooking = new Booking();
        correctBooking.setCar(carMock);
        correctBooking.setUser(userMock);
        correctBooking.setStartDateTime(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 4);
        correctBooking.setEndDateTime(cal.getTime());
        correctBooking.setPickupPosition(geometryFactory.createPoint(new Coordinate(1,1)));
        correctBooking.setDropPosition(geometryFactory.createPoint(new Coordinate(1,1)));
    }

    @Test
    public void canUpdateInfo_shouldReturnFailure_whenTimeIsNull()
    {
        when(bookingRepositoryMock.exists(anyLong())).thenReturn(true);

        MethodResult result = bookingService.canUpdateDropInfo(1, null, geometryFactory.createPoint(new Coordinate(1,1)));
        assertTrue(result.hasError(BookingErrors.dropTimeCannotBeNull));
    }
    @Test
    public void canUpdateInfo_shouldReturnFailure_whenPointIsNull()
    {
        when(bookingRepositoryMock.exists(anyLong())).thenReturn(true);

        MethodResult result = bookingService.canUpdateDropInfo(1, new Date(),null);
        assertTrue(result.hasError(BookingErrors.dropPointCannotBeNull));
    }

    @Test
    public void canUpdateInfo_shouldReturnFailure_whenDropTimeBeforePickupTime()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        Booking booking = new Booking();
        booking.setStartDateTime(cal.getTime());

        when(bookingRepositoryMock.exists(anyLong())).thenReturn(true);
        when(bookingRepositoryMock.get(anyLong())).thenReturn(booking);

        cal.set(Calendar.YEAR, 1999);
        MethodResult result = bookingService.canUpdateDropInfo(1, cal.getTime(),geometryFactory.createPoint(new Coordinate(1,1)));
        assertTrue(result.hasError(BookingErrors.dropTimeBeforeStartTime));
    }

    @Test
    public void canUpdateInfo_shouldReturnSuccess_whenEverythingIsAllright()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        Booking booking = new Booking();
        booking.setStartDateTime(cal.getTime());

        when(bookingRepositoryMock.exists(anyLong())).thenReturn(true);
        when(bookingRepositoryMock.get(anyLong())).thenReturn(booking);

        cal.set(Calendar.YEAR, 2001);
        MethodResult result = bookingService.canUpdateDropInfo(1, cal.getTime(),geometryFactory.createPoint(new Coordinate(1,1)));
        assertTrue(result.isSuccess());
    }

}
