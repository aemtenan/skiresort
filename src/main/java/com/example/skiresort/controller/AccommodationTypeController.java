package com.example.skiresort.controller;

import com.example.skiresort.model.AccommodationType;
import com.example.skiresort.repository.AccommodationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v2")
public class AccommodationTypeController {

    @Autowired
    AccommodationTypeRepository accommodationTypeRepository;

    @GetMapping("/accommodationtypes")
    public ResponseEntity<List<AccommodationType>> getAccommodationTypes(){

        List<AccommodationType> accommodationTypes = accommodationTypeRepository.findAll();

        if(accommodationTypes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accommodationTypes, HttpStatus.OK);
    }

    @GetMapping("/accommodationtypes/{id}")
    public ResponseEntity<AccommodationType> getAccommodationType(@PathVariable("id") long id){

        AccommodationType accommodationType = accommodationTypeRepository.findById(id).orElse(null);

        if(accommodationType==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(accommodationType, HttpStatus.OK);
    }

    @PostMapping("/accommodationtypes")
    public ResponseEntity<AccommodationType> addAccommodationType(@RequestBody AccommodationType accommodationType){
        AccommodationType accommodationTypeToAdd = accommodationTypeRepository.save(new AccommodationType(accommodationType.getName(), accommodationType.getRate(), accommodationType.getCapacity()));
        return new ResponseEntity<>(accommodationTypeToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/accommodationtypes/{id}")
    public ResponseEntity<AccommodationType> updateAccommodationType(@PathVariable("id") long id, @RequestBody AccommodationType accommodationType){
        AccommodationType accommodationTypeToUpdate = accommodationTypeRepository.findById(id).orElse(null);

        if(accommodationTypeToUpdate==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        accommodationTypeToUpdate.setName(accommodationType.getName());
        accommodationTypeToUpdate.setRate(accommodationType.getRate());
        accommodationTypeToUpdate.setCapacity(accommodationType.getCapacity());
        AccommodationType savedAccommodationType = accommodationTypeRepository.save(accommodationTypeToUpdate);
        return new ResponseEntity<>(savedAccommodationType, HttpStatus.OK);
    }

    @DeleteMapping("/accommodationtypes/{id}")
    public ResponseEntity<HttpStatus> deleteAccommodationType(@PathVariable("id") long id){
        accommodationTypeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
