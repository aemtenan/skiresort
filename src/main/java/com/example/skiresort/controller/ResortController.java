package com.example.skiresort.controller;

import com.example.skiresort.model.Resort;
import com.example.skiresort.repository.ResortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v2")
public class ResortController {

    @Autowired
    ResortRepository resortRepository;

    @GetMapping("/resorts")
    public ResponseEntity<Map<String, Object>> getResorts(@RequestParam(required = false) String town, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){

        List<Resort> resorts = new ArrayList<Resort>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Resort> pageResorts = null;

        if(town==null){
            pageResorts = resortRepository.findAll(pageable);
        }
        else{
            pageResorts = resortRepository.findByTownContaining(town, pageable);
        }

        if(pageResorts!=null){
            resorts = pageResorts.getContent();
        }

        if(resorts==null || resorts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Map<String, Object> pagedResponse = new HashMap<>();
        pagedResponse.put("resorts", resorts);
        pagedResponse.put("page-number", pageResorts.getNumber());
        pagedResponse.put("total-elements", pageResorts.getTotalElements());
        pagedResponse.put("total-pages", pageResorts.getTotalPages());

        return new ResponseEntity<>(pagedResponse, HttpStatus.OK);
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
        Resort savedResort = resortRepository.save(resortToUpdate);
        return new ResponseEntity<>(savedResort, HttpStatus.OK);
    }

    @DeleteMapping("/resorts/{id}")
    public ResponseEntity<HttpStatus> deleteResort(@PathVariable("id") long id){
        resortRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
