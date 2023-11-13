package com.example.skiresort.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "resorts")
public class Resort {

    @Id
    @GeneratedValue
    @Column(name = "resort_id")
    private Long id;
    private String name;
    private String town;

    Resort(){}

    public Resort(String name, String town){
        this.name = name;
        this.town = town;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getTown() {
        return town;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTown(String town){
        this.town = town;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Resort)) {
            return false;
        }
        Resort resort = (Resort) o;
        return Objects.equals(this.id, resort.id) && Objects.equals(this.name, resort.name) && Objects.equals(this.town, resort.town);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.town);
    }

    @Override
    public String toString(){
        String toReturn = "Resort id: "+id+", resort name: "+name+", resort town: "+town;
        return toReturn;
    }

}
