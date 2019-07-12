package com.shoter.ylper.api.Bookings;

import com.shoter.ylper.api.Common.Model;
import com.shoter.ylper.core.Bookings.Booking;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.util.Date;

public class BookingModel extends Model {
    private long id;
    @NotNull
    private long userId;
    @NotNull
    private long carId;
    @NotBlank
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}")
    private String startDateTime;
    @NotBlank
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}")
    private String endDateTime;
    @NotNull
    private double startPositionX;
    @NotNull
    private double startPositionY;
    @NotNull
    private double endPositionX;
    @NotNull
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
