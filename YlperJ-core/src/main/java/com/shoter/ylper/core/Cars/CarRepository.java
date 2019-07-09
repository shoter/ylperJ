package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Repository;

public interface CarRepository extends Repository {
    long getBookingsCountForCar(long carId);
    boolean exist(long carId);
    Car getCar(long carId);
}
