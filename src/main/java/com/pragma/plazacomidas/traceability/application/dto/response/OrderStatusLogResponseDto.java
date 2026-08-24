package com.pragma.plazacomidas.traceability.application.dto.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusLogResponseDto {
    private String previousStatus;
    private String newStatus;
    private Instant changedAt;
}
