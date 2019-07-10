package com.shoter.ylper.api.Cars;

import com.shoter.ylper.core.Cars.CarCompany;

public class CarCompanyModel {
    private int id;
    private String name;

    public CarCompanyModel(CarCompany comp)
    {
        id = comp.getId();
        name = comp.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
