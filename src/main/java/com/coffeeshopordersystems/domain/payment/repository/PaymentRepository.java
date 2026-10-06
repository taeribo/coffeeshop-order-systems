package com.coffeeshopordersystems.domain.payment.repository;

import com.coffeeshopordersystems.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
