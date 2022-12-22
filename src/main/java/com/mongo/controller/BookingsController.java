package com.mongo.controller;

import com.mongo.model.BookingRequest;
import com.mongo.model.BookingResponse;
import com.mongo.repo.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@Slf4j
public class BookingsController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    public Mono<BookingResponse> saveBooking(@RequestBody BookingRequest bookingRequest) {
        log.info("Received request for saveBooking");
        String bookingId = UUID.randomUUID().toString();
        bookingRequest.setBookingId(bookingId);
        return bookingRepository.save(bookingRequest)
                .map(e -> BookingResponse.builder().data("Booking Created with ID: " + e.getBookingId()).build());

    }

    @GetMapping("/{id}")
    public Mono<BookingResponse> getById(@PathVariable String id) {
        log.info("Received request for getById");
        return bookingRepository.findById(id).map(e -> BookingResponse.builder().data(e).build());
    }

    @GetMapping
    public Mono<BookingResponse> getAll() {
        log.info("Received request for getAll");
        return bookingRepository.findAll().collectList()
                .map(e -> BookingResponse.builder().data(e).build());
    }
}
