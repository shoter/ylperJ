package com.shoter.ylper.api.Cars;

import com.shoter.ylper.core.Cars.Car;

import javax.validation.constraints.NotNull;

public class CarShortModel {
    @NotNull
    long id;

    public CarShortModel(Car car)
    {
        id = car.getId();
    }

    public CarShortModel()
    {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
