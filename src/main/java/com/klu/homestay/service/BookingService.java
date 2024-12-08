package com.klu.homestay.service;

import com.klu.homestay.model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    Booking getBookingById(Long id);
    Booking saveBooking(Booking booking);
    Booking updateBooking(Long id, Booking booking);
    boolean deleteBooking(Long id);
}
