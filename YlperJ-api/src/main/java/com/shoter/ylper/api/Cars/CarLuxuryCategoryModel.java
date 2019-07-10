package com.shoter.ylper.api.Cars;

import com.shoter.ylper.core.Cars.CarLuxuryCategory;

public class CarLuxuryCategoryModel {
    private int id;
    private String name;

    public CarLuxuryCategoryModel(CarLuxuryCategory cat)
    {
        id = cat.getId();
        name = cat.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
