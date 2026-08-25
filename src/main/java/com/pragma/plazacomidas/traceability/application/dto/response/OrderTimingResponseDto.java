package com.pragma.plazacomidas.traceability.application.dto.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTimingResponseDto {
    private Long orderId;
    private Instant startedAt;
    private Instant endedAt;
}
