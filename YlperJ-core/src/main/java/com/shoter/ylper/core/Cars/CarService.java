package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Results.MethodResult;

public interface CarService  {

    MethodResult canAddCar(Car car);
    void addCar(Car car);

    MethodResult canRemoveCar(Car car);
    void removeCar(Car car);

    Car getCar(long id);

}
