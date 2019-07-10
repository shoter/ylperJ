package com.shoter.ylper.core.Cars;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Engines")
public class Engine {
    @Id
    @Column( name = "Id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FuelTypeId")
    @NotNull
    private FuelType fuelType;

    @Column(name = "Power")
    private int power;

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
