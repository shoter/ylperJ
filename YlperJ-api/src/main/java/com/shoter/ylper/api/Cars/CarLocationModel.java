package com.shoter.ylper.api.Cars;

import com.shoter.ylper.core.Cars.CarLocationHistory;

import java.util.Date;

public class CarLocationModel {
    Date dateTime;

    double x;

    double y;

    public CarLocationModel()
    {

    }

    public CarLocationModel(CarLocationHistory location)
    {
        dateTime = location.getCarLocationHistoryPK().getDateTime();
        x = location.getLocation().getX();
        y = location.getLocation().getY();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
