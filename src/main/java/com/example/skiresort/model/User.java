package com.example.skiresort.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String userName;

    private String email;

    User(){}

    public User(String userName, String email){
        this.userName = userName;
        this.email = email;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUserName(){
        return userName;
    }

    public String getEmail(){
        return email;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
