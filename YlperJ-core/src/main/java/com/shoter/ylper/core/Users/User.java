package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Bookings.Booking;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, updatable = false)
    private long Id;
    @Column(name = "Username", nullable = false, updatable = true, length = 50)
    @Size(max = 50)
    @NotBlank
    private String username;
    @Column(name="Name", nullable = false, updatable = true, length = 200)
    @Size(max=200)
    @NotBlank
    private String name;
    @NotNull
    @Column(name="Birthday", nullable = false, updatable = true)
    private Date birthDay;
    @NotNull
    @Column(name="CreateDate", updatable = false, nullable = false)
    private Date createDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GenderId")
    @NotNull
    private Gender gender;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "UserId")
    private Set<Booking> bookings;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "UserId")
    private Set<Booking> demands;


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
