package com.klu.homestay.service;
import com.klu.homestay.model.Payment;
import com.klu.homestay.repository.PaymentRepository;
import com.klu.homestay.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.orElse(null); // Return null if not found
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public boolean deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        }
        return false; // Return false if payment does not exist
    }
}
