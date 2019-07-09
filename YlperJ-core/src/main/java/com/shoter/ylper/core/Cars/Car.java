package com.shoter.ylper.core.Cars;

import com.shoter.ylper.core.Bookings.Booking;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @OneToOne
    @JoinColumn(name = "CarModelId")
    @NotNull
    private CarModel carModel;

    @Column(name = "CreateDate")
    @NotNull
    private Date createDate;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name="CarAssignedFeatures",
            joinColumns = @JoinColumn( name = "CarId"),
            inverseJoinColumns = @JoinColumn( name = "CarFeatureId")
    )
    private Set<CarFeature> carFeatures;

    @OneToMany
    @JoinColumn(name = "CarId")
    private Set<CarLocationHistory> carLocationHistories;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CarId")
    private Set<Booking> carBookings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<CarFeature> getCarFeatures() {
        return carFeatures;
    }

    public void setCarFeatures(Set<CarFeature> carFeatures) {
        this.carFeatures = carFeatures;
    }

    public Set<CarLocationHistory> getCarLocationHistories() {
        return carLocationHistories;
    }

    public void setCarLocationHistories(Set<CarLocationHistory> carLocationHistories) {
        this.carLocationHistories = carLocationHistories;
    }

    public Set<Booking> getCarBookings() {
        return carBookings;
    }

    public void setCarBookings(Set<Booking> carBookings) {
        this.carBookings = carBookings;
    }
}
