package com.shoter.ylper.core.Bookings;

import org.locationtech.jts.geom.Point;

import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.util.Date;

@NamedStoredProcedureQuery(
        name="Find_Car_For_Booking",
        procedureName="Find_Car_For_Booking",
        resultClasses = { FindCarProcedure.class },
        parameters={
                @StoredProcedureParameter(name="p_start_time", type= Date.class, mode= ParameterMode.IN),
                @StoredProcedureParameter(name="p_end_time", type= Date.class, mode= ParameterMode.IN),
                @StoredProcedureParameter(name="p_luxury_category_id", type= Integer.class, mode= ParameterMode.IN),
                @StoredProcedureParameter(name="p_car_features", type= String.class, mode= ParameterMode.IN),
                @StoredProcedureParameter(name="p_point", type= Point.class, mode= ParameterMode.IN)
        }
)
public class FindCarResult {
    private long carId;
    private double distance;
    private Point location;

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
