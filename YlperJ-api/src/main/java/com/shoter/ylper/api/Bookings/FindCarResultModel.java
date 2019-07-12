package com.shoter.ylper.api.Bookings;

import com.shoter.ylper.core.Bookings.FindCarResult;

public class FindCarResultModel {
    private long carId;
    private double locationX;
    private double locationY;
    private double distance;

    public FindCarResultModel(FindCarResult result)
    {
        carId = result.getCarId();
        locationX = result.getLocation().getX();
        locationY = result.getLocation().getY();
        distance = result.getDistance();
    }

    public long getCarId() {
        return carId;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public double getDistance() {
        return distance;
    }
}
