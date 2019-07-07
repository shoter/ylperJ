package com.shoter.ylper.core.Cars;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name = "CarLuxuryCategories")
public class CarLuxuryCategory {
    @Id
    @Column(name = "Id")
    private byte id;

    @Column(name = "Name")
    @Size(max = 50)
    @NotNull
    private String name;

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
