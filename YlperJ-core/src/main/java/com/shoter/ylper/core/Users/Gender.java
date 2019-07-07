package com.shoter.ylper.core.Users;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Genders")
public class Gender {
    @Id
    @Column(name="Id", nullable = false, updatable = false)
    private byte id;
    @Column(name = "Name", nullable = false, updatable = false)
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
