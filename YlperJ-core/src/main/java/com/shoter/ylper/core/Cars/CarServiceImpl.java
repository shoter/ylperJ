package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.ServiceBase;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CarServiceImpl extends ServiceBase implements CarService {

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
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

    public MethodResult canRemoveCar(Car car) {
        if(carRepository.exist(car.getId()) == false)
        {
            return new MethodResult("Car does not exist!");
        }

        MethodResult result = new MethodResult();

        if(carRepository.getBookingsCountForCar(car.getId()) > 0)
        {
            result.addError("You cannot remove a car that has bookings!");
        }
        return new MethodResult();
    }

    public void removeCar(final Car car) {
        carRepository.remove(car);
    }

    public Car getCar(final long id) {
        return carRepository.getCar(id);
    }
}
