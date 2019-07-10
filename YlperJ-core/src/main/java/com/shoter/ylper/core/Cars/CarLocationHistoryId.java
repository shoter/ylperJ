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

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;

        if(o == null || getClass() != o.getClass())
            return false;

        CarLocationHistoryId id = (CarLocationHistoryId)o;

        return id.getCarId() == getCarId() && id.getDateTime().equals(getDateTime());
    }

    @Override
    public int hashCode() {
        return new Long(carId).hashCode()+ dateTime.hashCode();
    }
}
