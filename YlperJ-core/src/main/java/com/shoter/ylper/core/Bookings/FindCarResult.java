package com.shoter.ylper.core.Bookings;

import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.Date;
@NamedStoredProcedureQueries(
@NamedStoredProcedureQuery(
        name = "findCar",
        procedureName = "Find_Car_For_Booking",
        resultClasses = FindCarResult.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "p_start_time"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "p_end_time"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Byte.class, name = "p_luxury_category_id"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_car_features"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Point.class, name = "p_point"),
        }
))
@Entity
public class FindCarResult {
    @Id
    @Column(name = "carId")
    private long carId;
    @Column(name = "distance")
    private double distance;
    @Column(name = "location")
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
