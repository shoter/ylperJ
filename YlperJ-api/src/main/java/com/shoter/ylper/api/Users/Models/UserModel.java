package com.shoter.ylper.api.Users.Models;

import com.shoter.ylper.api.Common.Model;
import com.shoter.ylper.core.Users.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserModel extends Model {
    private long id;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 200)
    private String name;
    @NotBlank
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}")
    private String birthday;
    @NotNull
    private Byte gender;

    public UserModel()
    {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public UserModel(User user) {
        this();
        id = user.getId();
        username = user.getUsername();
        name = user.getName();
        birthday = dateFormat.format(user.getBirthDay());
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Date getParsedBirthday() throws ParseException {
        return parseTime(birthday);
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }
}
