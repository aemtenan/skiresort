package com.example.skiresort.controller;

import com.example.skiresort.exception.DataConsistencyException;
import com.example.skiresort.model.Accommodation;
import com.example.skiresort.model.Booking;
import com.example.skiresort.model.Resort;
import com.example.skiresort.model.User;
import com.example.skiresort.repository.AccommodationRepository;
import com.example.skiresort.repository.BookingRepository;
import com.example.skiresort.repository.UserRepository;
import com.example.skiresort.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v2")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingService bookingService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccommodationRepository  accommodationRepository;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookings(){

        List<Booking> bookings = bookingRepository.findAll();

        if(bookings.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable("id") long id){

        Booking booking = bookingRepository.findById(id).orElse(null);

        if(booking==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/bookings")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable("id") long id){

        User user = userRepository.findById(id).orElse(null);

        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Booking> bookings = bookingRepository.findByUserId(id);

        if(bookings.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping("/users/{id}/accommodations/{accommodationid}/bookings")
    public ResponseEntity<Booking> addBooking(@PathVariable("id") long id, @PathVariable("accommodationid") long accommodationid, @RequestBody Booking booking){

        User user = userRepository.findById(id).orElse(null);

        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Accommodation accommodation = accommodationRepository.findById(accommodationid).orElse(null);

        if(accommodation==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        booking.setAccommodation(accommodation);
        booking.setUser(user);

        Booking bookingToAdd = bookingRepository.save(booking);
        return new ResponseEntity<>(bookingToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") long id, @RequestBody Booking booking){

        Booking bookingToUpdate = bookingRepository.findById(id).orElse(null);

        if(bookingToUpdate==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookingToUpdate.setCheckInDate(booking.getCheckInDate());
        bookingToUpdate.setCheckOutDate(booking.getCheckOutDate());

        try {
            Booking savedBooking = bookingService.updateBooking(bookingToUpdate);
            return new ResponseEntity<>(savedBooking, HttpStatus.OK);
        } catch (DataConsistencyException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<HttpStatus> deleteBooking(@PathVariable("id") long id){
        bookingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
