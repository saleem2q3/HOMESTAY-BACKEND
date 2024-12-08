package com.klu.homestay.controller;

import com.klu.homestay.model.Payment;
import com.klu.homestay.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        if (payments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<Payment> savePayment(@RequestBody Payment payment) {
        if (payment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Payment savedPayment = paymentService.savePayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        boolean isDeleted = paymentService.deletePayment(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found");
        }
        return ResponseEntity.ok("Payment deleted successfully");
    }
}
