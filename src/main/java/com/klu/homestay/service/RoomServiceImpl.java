package com.klu.homestay.service;

import com.klu.homestay.model.Room;
import com.klu.homestay.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    // Constructor injection for RoomRepository
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();  // Retrieve all rooms from the database
    }

    @Override
    public Room getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);  // Fetch room by ID
        return room.orElse(null);  // Return the room if found, otherwise null
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);  // Save the room into the database
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        if (roomRepository.existsById(id)) {
            room.setRoomId(id);  // Set the ID for the existing room
            return roomRepository.save(room);  // Update the room in the database
        }
        return null;  // Return null if the room does not exist
    }

    @Override
    public boolean deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);  // Delete room from the database
            return true;
        }
        return false;  // Return false if the room does not exist
    }
}
