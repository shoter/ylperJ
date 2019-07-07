package com.shoter.ylper.core.Cars;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CarCompanies")
public class CarCompany {

    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "name")
    @Size(max = 100)
    @NotNull
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
