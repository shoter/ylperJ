package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Results.MethodResult;

import java.util.List;

public interface CarService  {

    MethodResult canAddCar(Car car);
    void addCar(Car car);

    boolean exists(long carId);

    MethodResult canRemoveCar(long carId);
    void removeCar(long carId);

    Car getCar(long id);
    Car getFullCar(long id);

    MethodResult canInsertNewCarPosition(long carId, double x, double y);
    CarLocationHistory insertNewCarPosition(long carId, double x, double y);

    CarLocationHistory getLastCarPosition(long carId);

    List<Car> getCars();

}
