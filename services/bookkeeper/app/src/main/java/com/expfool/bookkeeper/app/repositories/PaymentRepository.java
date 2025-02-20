package com.expfool.bookkeeper.app.repositories;

import com.expfool.bookkeeper.app.entities.Payment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends ElasticsearchRepository<Payment, String> {
}
