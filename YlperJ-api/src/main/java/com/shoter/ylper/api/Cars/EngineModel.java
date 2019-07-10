package com.shoter.ylper.api.Cars;

import com.shoter.ylper.core.Cars.Engine;

public class EngineModel {
    int id;
    byte fuelTypeId;
    String fuelName;
    int power;

    public EngineModel()
    {

    }

    public EngineModel(Engine engine)
    {
        id = engine.getId();
        power = engine.getPower();
        fuelTypeId = engine.getFuelType().getId();
        fuelName = engine.getFuelType().getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(byte fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
