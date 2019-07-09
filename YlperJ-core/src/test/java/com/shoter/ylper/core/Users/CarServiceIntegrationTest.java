package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Bookings.Booking;
import com.shoter.ylper.core.Cars.*;
import com.shoter.ylper.core.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CarServiceIntegrationTest extends IntegrationTest {

    private CarService carService;

    @Override
    @BeforeEach
    public void beforeEachTest() {
        super.beforeEachTest();
        this.carService = new CarServiceImpl(new CarRepositoryImpl(session));
    }

    @Test
    public void addCar_shouldAddCar_andGetCar_shouldReturnItToUs()
    {
        Car car = new Car();
        car.setCarModel(session.load(CarModel.class, 1));
        car.setCreateDate(new Date());

        carService.addCar(car);

        Car addedCar = carService.getCar(car.getId());

        assertEquals(car.getCarModel().getId(), addedCar.getCarModel().getId());
        assertEquals(car.getCreateDate(), addedCar.getCreateDate());
    }

    @Test
    public void removeCar_shouldBeAbleTo_removeAddedCar()
    {
        Car car = new Car();
        car.setCarModel(session.load(CarModel.class, 1));
        car.setCreateDate(new Date());

        carService.addCar(car);
        long carId = car.getId();
        carService.removeCar(carId);

        assertNull(carService.getCar(carId));
    }

    @Test
    public void canRemoveCar_shouldReturnFalse_ifThereIsABooking()
    {
        Car car = new Car();
        car.setCarModel(session.load(CarModel.class, 1));
        car.setCreateDate(new Date());

        Set<Booking> bookings = new HashSet<Booking>();

        Booking booking = new Booking();
        booking.setDropPosition(geometryFactory.createPoint(new Coordinate()));
        booking.setPickupPosition(geometryFactory.createPoint(new Coordinate()));
        booking.setStartDateTime(new Date());
        booking.setEndDateTime(new Date());
        booking.setCar(car);
        booking.setUser(session.load(User.class, (long)1));

        bookings.add(booking);

        car.setCarBookings(bookings);

        carService.addCar(car);

        assertTrue(carService.canRemoveCar(car.getId()).hasError(CarErrors.cannotRemoveBecauseBookings));
    }

    @Test
    public void canRemoveCar_shouldReturnFalse_ifThereIsNoCar()
    {

    }
}
