package com.pragma.plazacomidas.traceability.application.handler;

import java.util.List;

import com.pragma.plazacomidas.traceability.application.dto.request.OrderStatusLogRequestDto;
import com.pragma.plazacomidas.traceability.application.dto.request.OrderTimingsRequestDto;
import com.pragma.plazacomidas.traceability.application.dto.response.OrderStatusLogResponseDto;
import com.pragma.plazacomidas.traceability.application.dto.response.OrderTimingResponseDto;

public interface ITraceabilityHandler {

    void logStatusChange(OrderStatusLogRequestDto orderStatusLogRequestDto);

    List<OrderStatusLogResponseDto> getTraceabilityByOrderId(Long orderId, Long authenticatedClientId);

    List<OrderTimingResponseDto> getOrderTimings(OrderTimingsRequestDto orderTimingsRequestDto);
}
