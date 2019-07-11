package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.Users.User;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table( name = "Bookings" )
public class Booking {
    @Id
    @Column( name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "UserId", nullable = false)
    @NotNull
    @OneToOne
    private User user;

    @JoinColumn(name = "CarId", nullable = false)
    @NotNull
    @OneToOne
    private Car car;

    @Column(name = "StartDateTime", nullable = false)
    @NotNull
    private Date startDateTime;

    @Column(name = "PickupPosition", nullable = false, columnDefinition = "geometry(POINT, 0)")
    @NotNull
    private Point pickupPosition;

    @Column(name = "EndDateTime")
    @NotNull
    private Date endDateTime;

    @Column(name = "DropPosition", columnDefinition = "geometry(POINT, 0)")
    @NotNull
    private Point dropPosition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Point getPickupPosition() {
        return pickupPosition;
    }

    public void setPickupPosition(Point pickupPosition) {
        this.pickupPosition = pickupPosition;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Point getDropPosition() {
        return dropPosition;
    }

    public void setDropPosition(Point dropPosition) {
        this.dropPosition = dropPosition;
    }

    @Override
    public int hashCode() {
        return new Long(id).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Booking)
        {
            return ((Booking)o).id == this.id;
        }

        return false;
    }
}
