package com.shoter.ylper.core.Cars;



import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table( name = "CarLocationHistories")
public class CarLocationHistory {
    @EmbeddedId
    private CarLocationHistoryId carLocationHistoryPK;

    @Column( name = "Location",columnDefinition = "geometry(POINT, 0)", nullable = false)
    @NotNull
    private Point location;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public CarLocationHistoryId getCarLocationHistoryPK() {
        return carLocationHistoryPK;
    }

    public void setCarLocationHistoryPK(CarLocationHistoryId carLocationHistoryPK) {
        this.carLocationHistoryPK = carLocationHistoryPK;
    }
}
