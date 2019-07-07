package com.shoter.ylper.core.Cars;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class CarLocationHistoryId implements Serializable {
    @Column(name = "CarId")
    protected long carId;
    @Column(name = "DateTime")
    protected Date dateTime;

    public CarLocationHistoryId()
    {}

    public CarLocationHistoryId(long carId, Date dateTime)
    {
        this.carId = carId;
        this.dateTime = dateTime;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
