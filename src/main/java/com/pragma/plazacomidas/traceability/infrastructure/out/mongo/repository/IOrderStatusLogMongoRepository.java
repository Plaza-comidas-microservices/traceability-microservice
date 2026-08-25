package com.pragma.plazacomidas.traceability.infrastructure.out.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pragma.plazacomidas.traceability.infrastructure.out.mongo.entity.OrderStatusLogDocument;

public interface IOrderStatusLogMongoRepository extends MongoRepository<OrderStatusLogDocument, String> {

    List<OrderStatusLogDocument> findByOrderId(Long orderId);

    List<OrderStatusLogDocument> findByOrderIdIn(List<Long> orderIds);
}
