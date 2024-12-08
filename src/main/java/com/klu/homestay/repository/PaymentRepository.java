package com.klu.homestay.repository;

import com.klu.homestay.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
