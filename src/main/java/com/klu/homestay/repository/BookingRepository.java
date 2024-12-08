package com.klu.homestay.repository;

import com.klu.homestay.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
