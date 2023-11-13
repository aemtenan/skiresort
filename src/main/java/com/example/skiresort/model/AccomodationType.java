package com.example.skiresort.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accomodation_type")
public class AccomodationType {

    @Id
    @GeneratedValue
    @Column(name = "accomodation_type_id")
    private Long id;
    private String name;
    private int rate;
    private int capacity;

   // @OneToMany(mappedBy = "accomodation_type")
   // private Set<Accomodation> accomodations = new HashSet<>();

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

    /*
    public Set<Accomodation> getAccomodations() {
        return accomodations;
    }

    public void setAccomodations(Set<Accomodation> accomodations) {
        this.accomodations = accomodations;
    }*/

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof AccomodationType)) {
            return false;
        }
        AccomodationType accomodationType = (AccomodationType) o;
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
