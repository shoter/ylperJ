package com.shoter.ylper.api.Cars;

import com.shoter.ylper.core.Cars.CarModel;

import java.math.BigDecimal;

// this name is stupid but I need to follow convention.
public class CarModelModel {
    int id;
    int designerCompanyId;
    String designerCompanyName;
    byte luxuryCategoryId;
    String luxuryCategoryName;
    String name;
    BigDecimal fuelConsumption;
    EngineModel engine;

    public EngineModel getEngine() {
        return engine;
    }

    public void setEngine(EngineModel engine) {
        this.engine = engine;
    }

    public CarModelModel() {

    }

    public CarModelModel(CarModel model)
    {
        id = model.getId();
        name = model.getName();
        fuelConsumption = model.getFuelConsumption();
        designerCompanyId = model.getDesignerCompany().getId();
        designerCompanyName = model.getDesignerCompany().getName();
        luxuryCategoryId = model.getLuxuryCategory().getId();
        luxuryCategoryName = model.getLuxuryCategory().getName();
        engine = new EngineModel(model.getEngine());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDesignerCompanyId() {
        return designerCompanyId;
    }

    public void setDesignerCompanyId(int designerCompanyId) {
        this.designerCompanyId = designerCompanyId;
    }

    public String getDesignerCompanyName() {
        return designerCompanyName;
    }

    public void setDesignerCompanyName(String designerCompanyName) {
        this.designerCompanyName = designerCompanyName;
    }

    public byte getLuxuryCategoryId() {
        return luxuryCategoryId;
    }

    public void setLuxuryCategoryId(byte luxuryCategoryId) {
        this.luxuryCategoryId = luxuryCategoryId;
    }

    public String getLuxuryCategoryName() {
        return luxuryCategoryName;
    }

    public void setLuxuryCategoryName(String luxuryCategoryName) {
        this.luxuryCategoryName = luxuryCategoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(BigDecimal fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
