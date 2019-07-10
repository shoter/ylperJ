package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.ServiceBase;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class CarServiceImpl extends ServiceBase implements CarService {

    private CarRepository carRepository;
    private GeometryFactory geometryFactory;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.geometryFactory = new GeometryFactory(new PrecisionModel(), 0);
    }

    public MethodResult canAddCar(Car car) {
        MethodResult result = new MethodResult();

        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        result.addError(violations);

        return result;
    }

    public void addCar(final Car car) {
        carRepository.add(car);
    }

    public boolean exists(long carId) {
        return carRepository.exist(carId);
    }

    public MethodResult canRemoveCar(final long carId) {
        if(carRepository.exist(carId) == false)
        {
            return new MethodResult(CarErrors.carNotExist);
        }

        MethodResult result = new MethodResult();

        if(carRepository.getBookingsCountForCar(carId) > 0)
        {
            result.addError(CarErrors.cannotRemoveBecauseBookings);
        }
        return result;
    }

    public void removeCar(final long carId) {
        carRepository.remove(carId);
    }

    public Car getCar(final long id) {
        return carRepository.getCar(id);
    }

    public Car getFullCar(long id) {
        return carRepository.getFullCar(id);
    }

    public MethodResult canInsertNewCarPosition(long carId, double x, double y) {
        if(carRepository.exist(carId) == false)
            return new MethodResult(CarErrors.carNotExist);

        return new MethodResult();
    }

    public CarLocationHistory insertNewCarPosition(long carId, double x, double y) {
        Point point = geometryFactory.createPoint(new Coordinate(x,y));

        return carRepository.insertNewPosition(carId, point);
    }

    public CarLocationHistory getLastCarPosition(long carId) {
        return carRepository.getLastCarHistory(carId);
    }

    public List<Car> getCars() {
        return carRepository.getCars();
    }
}
