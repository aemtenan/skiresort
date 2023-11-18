package com.example.skiresort.service;

import com.example.skiresort.exception.DataConsistencyException;
import com.example.skiresort.model.Booking;
import com.example.skiresort.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public Booking updateBooking(Booking booking) throws DataConsistencyException {
        try {
            return bookingRepository.save(booking);
        } catch (OptimisticLockingFailureException e) {
            throw new DataConsistencyException("Please retry, the booking was updated by another user");
        }
    }
}
