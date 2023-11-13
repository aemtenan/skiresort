package com.example.skiresort.controller;

import com.example.skiresort.model.Accommodation;
import com.example.skiresort.model.Resort;
import com.example.skiresort.repository.AccommodationRepository;
import com.example.skiresort.repository.ResortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v2")
public class AccommodationController {

    @Autowired
    ResortRepository resortRepository;

    @Autowired
    AccommodationRepository accommodationRepository;

    @GetMapping("/resorts/{id}/accommodations")
    public ResponseEntity<List<Accommodation>> getAccommodationsByResortId(@PathVariable("id") long id){
        if(!resortRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Accommodation> accommodations = accommodationRepository.findByResortId(id);
        if(accommodations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(accommodations, HttpStatus.OK);
    }

    @GetMapping("/accommodations/{id}")
    public ResponseEntity<Accommodation> getAccommodation(@PathVariable("id") long id){

        Accommodation accommodation = accommodationRepository.findById(id).orElse(null);

        if(accommodation==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(accommodation, HttpStatus.OK);
    }

    @PutMapping("/accommodations/{id}")
    public ResponseEntity<Accommodation> updateAccommodation(@PathVariable("id") long id, @RequestBody Accommodation accommodation){

        Accommodation accommodationToUpdate = accommodationRepository.findById(id).orElse(null);

        if(accommodationToUpdate==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        accommodationToUpdate.setOccupied(accommodation.getOccupied());
        Accommodation savedAccommodation = accommodationRepository.save(accommodationToUpdate);
        return new ResponseEntity<>(savedAccommodation, HttpStatus.OK);
    }

    @DeleteMapping("/accommodations/{id}")
    public ResponseEntity<HttpStatus> deleteAccommodation(@PathVariable("id") long id){
        accommodationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/resorts/{id}/accommodations")
    public ResponseEntity<Accommodation> addAccommodation(@PathVariable("id") long id, @RequestBody Accommodation accommodation){

        Resort resort = resortRepository.findById(id).orElse(null);

        if(resort==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        accommodation.setResort(resort);

        Accommodation accommodationToAdd = accommodationRepository.save(accommodation);

        return new ResponseEntity<>(accommodationToAdd, HttpStatus.CREATED);
    }

    @DeleteMapping("/resorts/{id}/accommodations")
    public ResponseEntity<List<Accommodation>> deleteAccommodationsByResortId(@PathVariable("id") long id){
        if(!resortRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accommodationRepository.deleteByResortId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
