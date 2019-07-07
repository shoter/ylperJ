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
}
