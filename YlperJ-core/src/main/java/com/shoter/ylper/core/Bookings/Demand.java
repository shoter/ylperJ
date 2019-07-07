package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Cars.Car;
import com.shoter.ylper.core.Cars.CarFeature;
import com.shoter.ylper.core.Users.User;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table( name = "Demands")
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @JoinColumn(name = "UserId", nullable = false)
    @NotNull
    @OneToOne
    private User user;

    @Column(name = "DesiredStartDateTime", nullable = false)
    @NotNull
    private Date desiredStartDateTime;

    @Column(name = "DesiredPickupLocation", nullable = false, columnDefinition = "geometry(POINT, 0)")
    @NotNull
    private Point desiredPickupLocation;

    @Column(name = "DesiredDropDateTime", nullable = false)
    @NotNull
    private Date desiredDropDateTime;

    @Column(name = "DesiredDropLocation", nullable = false, columnDefinition = "geometry(POINT, 0)")
    @NotNull
    private Point desiredDropLocation;

    @OneToMany
    @JoinTable(
            name="DemandFeatures",
            joinColumns = @JoinColumn( name = "DemandId"),
            inverseJoinColumns = @JoinColumn( name = "FeatureId")
    )
    private Set<CarFeature> desiredCarFeatures;

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

    public Date getDesiredStartDateTime() {
        return desiredStartDateTime;
    }

    public void setDesiredStartDateTime(Date desiredStartDateTime) {
        this.desiredStartDateTime = desiredStartDateTime;
    }

    public Point getDesiredPickupLocation() {
        return desiredPickupLocation;
    }

    public void setDesiredPickupLocation(Point desiredPickupLocation) {
        this.desiredPickupLocation = desiredPickupLocation;
    }

    public Date getDesiredDropDateTime() {
        return desiredDropDateTime;
    }

    public void setDesiredDropDateTime(Date desiredDropDateTime) {
        this.desiredDropDateTime = desiredDropDateTime;
    }

    public Point getDesiredDropLocation() {
        return desiredDropLocation;
    }

    public void setDesiredDropLocation(Point desiredDropLocation) {
        this.desiredDropLocation = desiredDropLocation;
    }

    public Set<CarFeature> getDesiredCarFeatures() {
        return desiredCarFeatures;
    }

    public void setDesiredCarFeatures(Set<CarFeature> desiredCarFeatures) {
        this.desiredCarFeatures = desiredCarFeatures;
    }
}
