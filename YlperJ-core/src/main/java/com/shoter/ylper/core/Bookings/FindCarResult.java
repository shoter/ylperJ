package com.shoter.ylper.core.Bookings;

import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.Date;

public class FindCarResult {
    @Id
    private long carId;
    private double distance;
    private Point location;

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
