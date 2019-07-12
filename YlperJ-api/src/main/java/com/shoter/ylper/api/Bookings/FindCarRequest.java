package com.shoter.ylper.api.Bookings;

import com.shoter.ylper.api.Common.Model;
import org.locationtech.jts.geom.Point;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class FindCarRequest extends Model {
    @NotNull
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}")
    private String startTime;
    @NotNull
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}")
    private String endTime;
    @NotNull
    private byte luxuryCategoryId;
    private List<Integer> carFeatureIds;
    @NotNull
    private double searchX;
    @NotNull
    private double searchY;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getParsedEndTime() throws ParseException {
        return parseTime(getEndTime());
    }

    public Date getParsedStartTime() throws ParseException {
        return parseTime(getStartTime());
    }

    public byte getLuxuryCategoryId() {
        return luxuryCategoryId;
    }

    public void setLuxuryCategoryId(byte luxuryCategoryId) {
        this.luxuryCategoryId = luxuryCategoryId;
    }

    public List<Integer> getCarFeatureIds() {
        return carFeatureIds;
    }

    public void setCarFeatureIds(List<Integer> carFeatureIds) {
        this.carFeatureIds = carFeatureIds;
    }

    public double getSearchX() {
        return searchX;
    }

    public void setSearchX(double searchX) {
        this.searchX = searchX;
    }

    public double getSearchY() {
        return searchY;
    }

    public void setSearchY(double searchY) {
        this.searchY = searchY;
    }
}
