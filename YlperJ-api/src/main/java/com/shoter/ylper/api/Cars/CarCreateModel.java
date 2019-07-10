package com.shoter.ylper.api.Cars;

import com.shoter.ylper.core.Cars.Car;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CarCreateModel extends CarShortModel {
    @NotNull
    private int carModelId;
    private List<Integer> carFeaturesIds;
    @NotNull
    private double x;
    @NotNull
    private double y;


    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public List<Integer> getCarFeaturesIds() {
        return carFeaturesIds;
    }

    public void setCarFeaturesIds(List<Integer> carFeaturesIds) {
        this.carFeaturesIds = carFeaturesIds;
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

