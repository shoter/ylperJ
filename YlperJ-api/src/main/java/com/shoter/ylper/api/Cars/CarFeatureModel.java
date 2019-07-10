package com.shoter.ylper.api.Cars;

import com.shoter.ylper.core.Cars.CarFeature;

public class CarFeatureModel {
    int id;
    String name;

    public CarFeatureModel()
    {

    }

    public CarFeatureModel(CarFeature feature)
    {
        id = feature.getId();
        name = feature.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
