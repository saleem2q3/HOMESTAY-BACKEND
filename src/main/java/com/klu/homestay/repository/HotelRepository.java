package com.klu.homestay.repository;

import com.klu.homestay.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
