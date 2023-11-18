package com.example.skiresort.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Booking booking;

    private int amount;

    private Date paymentDate;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Date getPaymentDate(){
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate){
        this.paymentDate = paymentDate;
    }

    public Booking getBooking(){
        return booking;
    }

    private void setBooking(Booking booking){
        this.booking = booking;
    }
}
