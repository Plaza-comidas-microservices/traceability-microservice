package com.pragma.plazacomidas.traceability.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusLogRequestDto {
    private Long orderId;
    private Long clientId;
    private String previousStatus;
    private String newStatus;
}
