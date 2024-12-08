package com.klu.homestay.service;

import com.klu.homestay.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment savePayment(Payment payment);
    boolean deletePayment(Long id);
}
