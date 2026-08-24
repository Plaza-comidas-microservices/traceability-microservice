package com.pragma.plazacomidas.traceability.application.handler;

import java.util.List;

import com.pragma.plazacomidas.traceability.application.dto.request.OrderStatusLogRequestDto;
import com.pragma.plazacomidas.traceability.application.dto.response.OrderStatusLogResponseDto;

public interface ITraceabilityHandler {

    void logStatusChange(OrderStatusLogRequestDto orderStatusLogRequestDto);

    List<OrderStatusLogResponseDto> getTraceabilityByOrderId(Long orderId, Long authenticatedClientId);
}
