package com.pragma.plazacomidas.traceability.infrastructure.out.mongo.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "order_status_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusLogDocument {

    @Id
    private String id;
    private Long orderId;
    private Long clientId;
    private String previousStatus;
    private String newStatus;
    private Instant changedAt;
}
