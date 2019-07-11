package com.shoter.ylper.core.Cars;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "FuelTypes")
public class FuelType {
    @Id
    @Column(name = "Id")
    private byte id;

    @Size(max = 40)
    @Column(name = "Name")
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

    @Override
    public int hashCode() {
        return new Byte(id).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof FuelType)
        {
            return ((FuelType)o).id == this.id;
        }

        return false;
    }
}
