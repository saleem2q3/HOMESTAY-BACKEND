package com.klu.homestay.controller;

import com.klu.homestay.model.Booking;
import com.klu.homestay.model.Room;
import com.klu.homestay.service.BookingService;
import com.klu.homestay.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService; // Inject RoomService

    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService; // Initialize RoomService
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(booking);
    }

    @PostMapping
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking) {
        if (booking == null || booking.getRoom() == null || booking.getRoom().getRoomId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Ensure the Room object is already saved in the database
        if (booking.getRoom().getRoomId() == null) {
            // Save the Room first if not already saved
            roomService.saveRoom(booking.getRoom());
        }

        // Save the Booking after ensuring the Room is saved
        Booking savedBooking = bookingService.saveBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        if (booking == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Booking updatedBooking = bookingService.updateBooking(id, booking);
        if (updatedBooking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        boolean isDeleted = bookingService.deleteBooking(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
        return ResponseEntity.ok("Booking deleted successfully");
    }
}
