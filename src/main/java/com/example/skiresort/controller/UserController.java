package com.example.skiresort.controller;

import com.example.skiresort.model.User;
import com.example.skiresort.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v2")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){

        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){

        User user = userRepository.findById(id).orElse(null);

        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User userToAdd = userRepository.save(new User(user.getUserName(), user.getEmail()));
        return new ResponseEntity<>(userToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
        User userToUpdate = userRepository.findById(id).orElse(null);

        if(userToUpdate==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userToUpdate.setUserName(user.getUserName());
        userToUpdate.setEmail(user.getEmail());
        User savedUser = userRepository.save(userToUpdate);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
