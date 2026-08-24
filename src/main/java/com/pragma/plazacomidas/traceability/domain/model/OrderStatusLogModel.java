package com.pragma.plazacomidas.traceability.domain.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusLogModel {
    private String id;
    private Long orderId;
    private Long clientId;
    private String previousStatus;
    private String newStatus;
    private Instant changedAt;
}
