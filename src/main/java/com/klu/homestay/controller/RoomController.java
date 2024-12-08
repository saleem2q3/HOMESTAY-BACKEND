	package com.klu.homestay.controller;
	
	import com.klu.homestay.model.Room;
	import com.klu.homestay.service.RoomService;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	
	import java.util.List;
	
	@RestController
	@RequestMapping("/rooms")
	@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
	public class RoomController {
	
	    private final RoomService roomService;
	
	    public RoomController(RoomService roomService) {
	        this.roomService = roomService;
	    }
	
	    @GetMapping
	    public ResponseEntity<List<Room>> getAllRooms() {
	        List<Room> rooms = roomService.getAllRooms();
	        if (rooms.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.ok(rooms);
	    }
	
	    @GetMapping("/{id}")
	    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
	        Room room = roomService.getRoomById(id);
	        if (room == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	        return ResponseEntity.ok(room);
	    }
	
	    @PostMapping
	    public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
	        // Ensure the hotel is set and valid before saving the room
	        if (room.getHotel() == null || room.getHotel().getHotelId() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                                 .body(null);
	        }
	
	        Room savedRoom = roomService.saveRoom(room);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
	    }
	
	    @PutMapping("/{id}")
	    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
	        if (room == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }
	
	        Room updatedRoom = roomService.updateRoom(id, room);
	        if (updatedRoom == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	        return ResponseEntity.ok(updatedRoom);
	    }
	
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
	        boolean isDeleted = roomService.deleteRoom(id);
	        if (!isDeleted) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
	        }
	        return ResponseEntity.ok("Room deleted successfully");
	    }
	}
