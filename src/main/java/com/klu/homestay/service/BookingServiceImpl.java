package com.klu.homestay.service;

import com.klu.homestay.model.Booking;
import com.klu.homestay.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.orElse(null);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        // Removed the check for customer, as it is no longer part of the Booking
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long id, Booking booking) {
        if (!bookingRepository.existsById(id)) {
            return null;  // Booking not found
        }
        booking.setBookingId(id);  // Ensure the correct ID is updated
        return bookingRepository.save(booking);
    }

    @Override
    public boolean deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            return false;  // Booking not found
        }
        bookingRepository.deleteById(id);
        return true;
    }
}
