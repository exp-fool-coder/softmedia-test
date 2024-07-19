package com.expfool.bookkeeper.repositories;

import com.expfool.bookkeeper.Entities.Payment;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
interface PaymentRepository extends Repository<Payment, String> {
}
