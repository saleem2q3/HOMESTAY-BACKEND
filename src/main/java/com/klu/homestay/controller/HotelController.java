package com.klu.homestay.controller;

import com.klu.homestay.model.Hotel;
import com.klu.homestay.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        if (hotels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(hotel);
    }

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
        if (hotel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Hotel savedHotel = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        if (hotel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);
        if (updatedHotel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        boolean isDeleted = hotelService.deleteHotel(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found");
        }
        return ResponseEntity.ok("Hotel deleted successfully");
    }
}
