package com.shoter.ylper.core.Cars;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "CarModels")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @OneToOne
    @JoinColumn(name = "DesignerCompanyId")
    @NotNull
    private CarCompany designerCompany;

    @OneToOne
    @JoinColumn(name = "EngineId")
    @NotNull
    private Engine engine;

    @OneToOne
    @JoinColumn(name = "LuxuryCategoryId")
    @NotNull
    private CarLuxuryCategory luxuryCategory;

    @Column(name = "Name")
    @Size(max = 100)
    @NotNull
    private String name;

    @Column(name = "FuelConsumption", precision = 5, scale = 2)
    @NotNull
    private BigDecimal fuelConsumption;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarCompany getDesignerCompany() {
        return designerCompany;
    }

    public void setDesignerCompany(CarCompany designerCompany) {
        this.designerCompany = designerCompany;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public CarLuxuryCategory getLuxuryCategory() {
        return luxuryCategory;
    }

    public void setLuxuryCategory(CarLuxuryCategory luxuryCategory) {
        this.luxuryCategory = luxuryCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(BigDecimal fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public int hashCode() {
        return new Integer(id).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof CarModel)
        {
            return ((CarModel)o).id == id;
        }

        return false;
    }
}
