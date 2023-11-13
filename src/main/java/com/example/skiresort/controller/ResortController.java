package com.example.skiresort.controller;

import com.example.skiresort.model.Resort;
import com.example.skiresort.repository.ResortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v2")
public class ResortController {

    @Autowired
    ResortRepository resortRepository;

    @GetMapping("/resorts")
    public ResponseEntity<List<Resort>> getResorts(){

        List<Resort> resorts = resortRepository.findAll();

        if(resorts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resorts, HttpStatus.OK);
    }

    @GetMapping("/resorts/{id}")
    public ResponseEntity<Resort> getResort(@PathVariable("id") long id){

        Resort resort = resortRepository.findById(id).orElse(null);

        if(resort==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resort, HttpStatus.OK);
    }

    @PostMapping("/resorts")
    public ResponseEntity<Resort> addResort(@RequestBody Resort resort){
        Resort resortToAdd = resortRepository.save(new Resort(resort.getName(), resort.getTown()));
        return new ResponseEntity<>(resortToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/resorts/{id}")
    public ResponseEntity<Resort> updateResort(@PathVariable("id") long id, @RequestBody Resort resort){
        Resort resortToUpdate = resortRepository.findById(id).orElse(null);

        if(resortToUpdate==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        resortToUpdate.setName(resort.getName());
        resortToUpdate.setTown(resort.getTown());
        return new ResponseEntity<>(resortToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/resorts/{id}")
    public ResponseEntity<HttpStatus> deleteResort(@PathVariable("id") long id){
        resortRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
