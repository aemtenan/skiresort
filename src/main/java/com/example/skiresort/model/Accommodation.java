package com.example.skiresort.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "accomodations")
public class Accommodation {

    @Id
    @GeneratedValue
    @Column(name = "accomodation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resort_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Resort resort;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "accomodation_type_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    //private AccomodationType accomodationType;

    private String occupied;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getOccupied(){
        return occupied;
    }

    public void setOccupied(String occupied){
        this.occupied = occupied;
    }

    public Resort getResort() {
        return resort;
    }

    public void setResort(Resort resort) {
        this.resort = resort;
    }

    //public AccomodationType getAccomodationType() {
    //    return accomodationType;
    //}

    //public void setAccomodationType(AccomodationType accomodationType) {
    //    this.accomodationType = accomodationType;
    //}
}