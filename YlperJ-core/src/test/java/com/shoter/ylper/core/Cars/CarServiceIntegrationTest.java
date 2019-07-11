package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Bookings.Booking;
import com.shoter.ylper.core.Cars.*;
import com.shoter.ylper.core.IntegrationTest;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.Users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CarServiceIntegrationTest extends IntegrationTest {

    private CarService carService;
    private Car correctCar;

    @Override
    @BeforeEach
    public void beforeEachTest() {
        super.beforeEachTest();
        this.carService = new CarServiceImpl(new CarRepositoryImpl(session));

        this.correctCar = new Car();
        this.correctCar.setCarModel(session.load(CarModel.class, 1));
        this.correctCar.setCreateDate(new Date());
        Set<CarFeature> features = new HashSet<CarFeature>();
        features.add(session.load(CarFeature.class, 2));
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
    public void deleteCar_shouldDeleteCar()
    {
        carService.addCar(this.correctCar);
        carService.insertNewCarPosition(this.correctCar.getId(), 5, 12);

        long id = this.correctCar.getId();
        carService.removeCar(this.correctCar);

        assertFalse(carService.exists(id));

    }

    @Test
    public void addCar_withoutFeatures_shouldAddCar_andGetFullCar_shouldReturnItToUs()
    {
        Car car = new Car();
        car.setCarModel(session.load(CarModel.class, 1));
        car.setCreateDate(new Date());

        carService.addCar(car);

        Car addedCar = carService.getFullCar(car.getId());

        assertNotNull(addedCar);
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
        carService.removeCar(car);

        assertNull(carService.getCar(carId));
    }

    @Test
    public void getLastCarPosition_shouldReturnLastPosition_thatHasBeenAddedToDatabase() throws InterruptedException {
        Car car = carService.getCar(1);

        double x = 1;
        double y= 2;

        carService.insertNewCarPosition(car.getId(), x, y);
        CarLocationHistory db = carService.getLastCarPosition(car.getId());
        assertEquals(x, db.getLocation().getX(), 0.01);
        assertEquals(y, db.getLocation().getY(), 0.01);

        //MySQL database could not handle very short interval and where not sorting this information correctly :(.
        Thread.sleep(1200);

        x = 5; y = 2;
        carService.insertNewCarPosition(car.getId(), x, y);
        db = carService.getLastCarPosition(car.getId());
        assertEquals(x, db.getLocation().getX(), 0.01);
        assertEquals(y, db.getLocation().getY(), 0.01);
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

        MethodResult result = carService.canRemoveCar(car.getId());
        assertTrue(result.hasError(CarErrors.cannotRemoveBecauseBookings));
    }

    @Test
    public void canRemoveCar_shouldReturnFalse_ifThereIsNoCar()
    {
        assertTrue(carService.canRemoveCar(-123456).hasError(CarErrors.carNotExist));
    }

    @Test
    public void getFullCar_shouldNotThrowException()
    {
        Car car =carService.getFullCar(1);
        // let's access fuel just for test.
        FuelType fuel = car.getCarModel().getEngine().getFuelType();
    }
}
