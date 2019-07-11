package com.shoter.ylper.api.Cars;

import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.Cars.CarFeature;
import com.shoter.ylper.core.Cars.CarLocationHistory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarModel {
    long id;
    List<CarFeatureModel> carFeatures;
    Date createDate;
    CarModelModel carModel;
    CarLocationModel location;


    public CarModel(Car car, CarLocationHistory locationHistory)
    {
        id = car.getId();
        createDate = car.getCreateDate();
        carModel = new CarModelModel(car.getCarModel());

        if(car.getCarFeatures() != null) {
            carFeatures = new ArrayList<CarFeatureModel>(car.getCarFeatures().size());
            for (CarFeature f : car.getCarFeatures()) {
                carFeatures.add(new CarFeatureModel(f));
            }
        }

        if(locationHistory != null)
        location = new CarLocationModel(locationHistory);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CarFeatureModel> getCarFeatures() {
        return carFeatures;
    }

    public void setCarFeatures(List<CarFeatureModel> carFeatures) {
        this.carFeatures = carFeatures;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public CarModelModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModelModel carModel) {
        this.carModel = carModel;
    }

    public CarLocationModel getLocation() {
        return location;
    }

    public void setLocation(CarLocationModel location) {
        this.location = location;
    }
}
