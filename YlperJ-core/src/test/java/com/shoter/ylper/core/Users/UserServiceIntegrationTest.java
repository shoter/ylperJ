package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Bookings.Booking;
import com.shoter.ylper.core.Bookings.BookingRepositoryImpl;
import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.Demands.Demand;
import com.shoter.ylper.core.Demands.DemandRepositoryImpl;
import com.shoter.ylper.core.IntegrationTest;
import com.shoter.ylper.core.Results.MethodResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceIntegrationTest extends IntegrationTest {

    private UserService userService;

    private User correctUser;

    @Override
    @BeforeEach
    public void beforeEachTest() {
        super.beforeEachTest();

        userService = new UserServiceImpl(new UserRepositoryImpl(session), new DemandRepositoryImpl(session), new BookingRepositoryImpl(session));
        this.correctUser = new User();

        correctUser.setId(1);
        correctUser.setCreateDate(new Date());
        correctUser.setBirthDay(new Date());
        correctUser.setName("alladin");
        correctUser.setUsername("all");
        correctUser.setGender(session.load(Gender.class, (byte)1));
    }

    @Test
    public void getUser_shouldReturnUser_afterAddUser()
    {
        userService.addUser(correctUser);
        User addedUser = userService.getUser(correctUser.getId());

        assertEquals(correctUser.getName(), addedUser.getName());
        assertEquals(correctUser.getUsername(), addedUser.getUsername());
        assertEquals(correctUser.getBirthDay(), addedUser.getBirthDay());
        assertEquals(correctUser.getGender().getId(), addedUser.getGender().getId());
        assertEquals(correctUser.getCreateDate(), addedUser.getCreateDate());
    }

    @Test
    public void canRemoveUser_shouldReturnErroor_whenUserIsNull()
    {
        MethodResult result = userService.canRemoveUser(null);

        assertTrue(result.hasError(UserErrors.userNotExist));
    }

    @Test
    public void canRemoveUser_shouldReturnErroor_whenUserDoesNotExist()
    {
        User user = session.load(User.class, (long)-12345);
        MethodResult result = userService.canRemoveUser(user);

        assertTrue(result.hasError(UserErrors.userNotExist));
    }

    @Test
    public void canRemoveUser_shouldReturnError_whenUserHasBookings()
    {
        userService.addUser(correctUser);
        Car testCar = session.load(Car.class, (long)1);

        Booking booking = new Booking();
        booking.setCar(testCar);
        booking.setUser(correctUser);
        booking.setStartDateTime(new Date());
        booking.setPickupPosition(geometryFactory.createPoint(new Coordinate(1,2)));

        session.beginTransaction();
        session.save(booking);
        session.getTransaction().commit();

        MethodResult result = userService.canRemoveUser(correctUser);

        assertTrue(result.hasError(UserErrors.cannotRemoveBecauseOfBookings));
    }

    @Test
    public void canRemoveUser_shouldReturnError_whenUserHasDemands()
    {
        userService.addUser(correctUser);
        Car testCar = session.load(Car.class, (long)1);

        Demand demand = new Demand();
        demand.setUser(correctUser);
        demand.setDesiredStartDateTime(new Date());
        demand.setDesiredPickupLocation(geometryFactory.createPoint(new Coordinate(1,2)));
        demand.setDesiredDropLocation(geometryFactory.createPoint(new Coordinate(1,2)));
        demand.setDesiredDropDateTime(new Date());

        session.beginTransaction();
        session.save(demand);
        session.getTransaction().commit();

        MethodResult result = userService.canRemoveUser(correctUser);
        assertTrue(result.hasError(UserErrors.cannotRemoveBecauseOfDemands));
    }

    @Test
    public void canRemoveUser_shouldReturnSuccess_forCorrectUsersWithoutBookingsNorDemands()
    {
        userService.addUser(correctUser);

        MethodResult result = userService.canRemoveUser(correctUser);

        assertTrue(result.isSuccess());
    }

    @Test
    public void removeUser_shouldRemoveCorrectUser()
    {
        userService.addUser(correctUser);
        long id = correctUser.getId();

        userService.removeUser(correctUser);

        assertNull(userService.getUser(id));
    }

    @Test
    public void getUser_thatDoesNotExist_shouldReturnNull()
    {
        User user = userService.getUser(-9999999);
        assertNull(user);
    }
}
