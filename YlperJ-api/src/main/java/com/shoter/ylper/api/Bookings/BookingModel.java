package com.shoter.ylper.api.Bookings;

import com.shoter.ylper.api.Common.Model;
import com.shoter.ylper.core.Bookings.Booking;

import java.text.ParseException;
import java.util.Date;

public class BookingModel extends Model {
    private long id;
    private long userId;
    private long carId;
    private String startDateTime;
    private String endDateTime;
    private double startPositionX;
    private double startPositionY;
    private double endPositionX;
    private double endPositionY;

    public BookingModel()
    {

    }

    public BookingModel(Booking booking)
    {
        id = booking.getId();
        userId = booking.getUser().getId();
        carId = booking.getCar().getId();
        startDateTime = dateFormat.format(booking.getStartDateTime());
        endDateTime = dateFormat.format(booking.getEndDateTime());
        startPositionX = booking.getPickupPosition().getX();
        startPositionY = booking.getPickupPosition().getY();
        endPositionX = booking.getDropPosition().getX();
        endPositionY = booking.getDropPosition().getY();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Date getParsedEndDate() throws ParseException {
        return parseTime(endDateTime);
    }

    public Date getParsedStartDate() throws ParseException {
        return parseTime(startDateTime);
    }

    public double getStartPositionX() {
        return startPositionX;
    }

    public void setStartPositionX(double startPositionX) {
        this.startPositionX = startPositionX;
    }

    public double getStartPositionY() {
        return startPositionY;
    }

    public void setStartPositionY(double startPositionY) {
        this.startPositionY = startPositionY;
    }

    public double getEndPositionX() {
        return endPositionX;
    }

    public void setEndPositionX(double endPositionX) {
        this.endPositionX = endPositionX;
    }

    public double getEndPositionY() {
        return endPositionY;
    }

    public void setEndPositionY(double endPositionY) {
        this.endPositionY = endPositionY;
    }
}
