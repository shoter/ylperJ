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
}
