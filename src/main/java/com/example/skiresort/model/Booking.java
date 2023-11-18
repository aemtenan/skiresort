package com.example.skiresort.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue
    @Column(name = "booking_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accommodation_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Accommodation accommodation;

    private Date checkInDate;

    private Date checkOutDate;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Date getCheckInDate(){
        return checkInDate;
    }

    public Date getCheckOutDate(){
        return checkOutDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Accommodation getAccommodation() {return accommodation;}

    public void setAccommodation(Accommodation accommodation) {this.accommodation = accommodation;}
}