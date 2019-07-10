package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Repository;
import org.locationtech.jts.geom.Point;

public interface CarRepository extends Repository {
    long getBookingsCountForCar(long carId);
    boolean exist(long carId);
    Car getCar(long carId);
    void remove(long carId);
    CarLocationHistory insertNewPosition(long carId, Point point);
    CarLocationHistory getLastCarHistory(long carId);
}
