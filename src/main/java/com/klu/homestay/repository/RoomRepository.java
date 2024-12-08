package com.klu.homestay.repository;

import com.klu.homestay.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
