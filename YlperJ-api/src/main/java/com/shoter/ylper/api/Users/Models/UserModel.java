package com.shoter.ylper.api.Users.Models;

import com.shoter.ylper.core.Users.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserModel {
    private long id;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 200)
    private String name;
    @NotNull
    private Date birthday;
    @NotNull
    private Byte gender;

    public UserModel()
    {

    }

    public UserModel(User user) {
        id = user.getId();
        username = user.getUsername();
        name = user.getName();
        birthday = user.getBirthDay();
        gender = user.getGender().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }
}
