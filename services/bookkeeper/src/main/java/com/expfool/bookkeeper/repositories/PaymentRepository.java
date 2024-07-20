package com.expfool.bookkeeper.repositories;

import com.expfool.bookkeeper.entities.Payment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends ElasticsearchRepository<Payment, String> {
}
