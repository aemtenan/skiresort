package com.example.skiresort.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "accommodations")
public class Accommodation {

    @Id
    @GeneratedValue
    @Column(name = "accommodation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resort_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Resort resort;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accommodationtype_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AccommodationType accommodationType;

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

    public AccommodationType getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(AccommodationType accommodationType) {
        this.accommodationType = accommodationType;
    }
}
