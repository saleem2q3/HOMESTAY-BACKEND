package com.klu.homestay.service;

import com.klu.homestay.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();

    Room getRoomById(Long id);

    Room saveRoom(Room room);

    Room updateRoom(Long id, Room room);

    boolean deleteRoom(Long id);
    
}
