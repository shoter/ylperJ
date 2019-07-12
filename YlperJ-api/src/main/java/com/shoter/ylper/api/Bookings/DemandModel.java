package com.shoter.ylper.api.Bookings;

import com.shoter.ylper.api.Cars.CarFeatureModel;
import com.shoter.ylper.api.Common.Model;
import com.shoter.ylper.core.Cars.CarFeature;
import com.shoter.ylper.core.Demands.Demand;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DemandModel extends Model {
    long id;
    @NotNull
    private long userId;
    @NotNull
    private double desiredPickupLocationX;
    @NotNull
    private double desiredPickupLocationY;
    @NotNull
    private String desiredStartTime;
    @NotNull
    private double desiredDropLocationX;
    @NotNull
    private double desiredDropLocationY;
    @NotNull
    private String desiredEndDate;
    private List<CarFeatureModel> desiredCarFeatures;

    public DemandModel()
    {

    }

    public DemandModel(Demand demand)
    {
        id = demand.getId();
        userId = demand.getUser().getId();
        desiredPickupLocationX = demand.getDesiredPickupLocation().getX();
        desiredPickupLocationY = demand.getDesiredPickupLocation().getY();
        desiredStartTime = dateFormat.format(demand.getDesiredStartDateTime());
        desiredDropLocationX = demand.getDesiredDropLocation().getX();
        desiredDropLocationY = demand.getDesiredDropLocation().getY();
        desiredEndDate = dateFormat.format(demand.getDesiredDropDateTime());

        if(demand.getDesiredCarFeatures() != null)
        {
            desiredCarFeatures = new ArrayList<CarFeatureModel>(demand.getDesiredCarFeatures().size());
            for(CarFeature f : demand.getDesiredCarFeatures())
            {
                desiredCarFeatures.add(new CarFeatureModel(f));
            }
        }
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

    public double getDesiredPickupLocationX() {
        return desiredPickupLocationX;
    }

    public void setDesiredPickupLocationX(double desiredPickupLocationX) {
        this.desiredPickupLocationX = desiredPickupLocationX;
    }

    public double getDesiredPickupLocationY() {
        return desiredPickupLocationY;
    }

    public void setDesiredPickupLocationY(double desiredPickupLocationY) {
        this.desiredPickupLocationY = desiredPickupLocationY;
    }

    public double getDesiredDropLocationX() {
        return desiredDropLocationX;
    }

    public void setDesiredDropLocationX(double desiredDropLocationX) {
        this.desiredDropLocationX = desiredDropLocationX;
    }

    public double getDesiredDropLocationY() {
        return desiredDropLocationY;
    }

    public void setDesiredDropLocationY(double desiredDropLocationY) {
        this.desiredDropLocationY = desiredDropLocationY;
    }

    public List<CarFeatureModel> getDesiredCarFeatures() {
        return desiredCarFeatures;
    }

    public void setDesiredCarFeatures(List<CarFeatureModel> desiredCarFeatures) {
        this.desiredCarFeatures = desiredCarFeatures;
    }

    public String getDesiredStartTime() {
        return desiredStartTime;
    }

    public void setDesiredStartTime(String desiredStartTime) {
        this.desiredStartTime = desiredStartTime;
    }

    public String getDesiredEndDate() {
        return desiredEndDate;
    }

    public void setDesiredEndDate(String desiredEndDate) {
        this.desiredEndDate = desiredEndDate;
    }

    public Date getParsedEndDate() throws ParseException {
        return parseTime(desiredEndDate);
    }

    public Date getParsedStartDate() throws ParseException {
        return parseTime(desiredStartTime);
    }
}
