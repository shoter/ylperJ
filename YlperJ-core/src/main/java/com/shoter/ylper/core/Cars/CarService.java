package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Results.MethodResult;

public interface CarService  {

    MethodResult canAddCar(Car car);
    void addCar(Car car);

    MethodResult canRemoveCar(long carId);
    void removeCar(long carId);

    Car getCar(long id);

}
