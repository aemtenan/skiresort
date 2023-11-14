package com.example.skiresort.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "accommodationtypes")
public class AccommodationType {

    @Id
    @GeneratedValue
    @Column(name = "accommodationtype_id")
    private Long id;
    private String name;
    private int rate;
    private int capacity;

    AccommodationType(){
    }

    public AccommodationType(String name, int rate, int capacity){
        this.name = name;
        this.rate = rate;
        this.capacity = capacity;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getRate() {
        return rate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRate(int rate){
        this.rate = rate;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof AccommodationType)) {
            return false;
        }
        AccommodationType accomodationType = (AccommodationType) o;
        return Objects.equals(this.id, accomodationType.id) && Objects.equals(this.name, accomodationType.name) && Objects.equals(this.rate, accomodationType.rate) && Objects.equals(this.capacity, accomodationType.capacity);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.rate, this.capacity);
    }

    @Override
    public String toString(){
        String toReturn = "AccomodationType id: "+id+", AccomodationType name: "+name+", AccomodationType rate: "+rate+", AccomodationType capacity: "+capacity;
        return toReturn;
    }

}
