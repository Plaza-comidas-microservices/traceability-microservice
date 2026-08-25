package com.pragma.plazacomidas.traceability.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pragma.plazacomidas.traceability.application.dto.request.OrderStatusLogRequestDto;
import com.pragma.plazacomidas.traceability.application.dto.request.OrderTimingsRequestDto;
import com.pragma.plazacomidas.traceability.application.dto.response.OrderStatusLogResponseDto;
import com.pragma.plazacomidas.traceability.application.dto.response.OrderTimingResponseDto;
import com.pragma.plazacomidas.traceability.application.handler.ITraceabilityHandler;
import com.pragma.plazacomidas.traceability.application.mapper.IOrderStatusLogRequestMapper;
import com.pragma.plazacomidas.traceability.application.mapper.IOrderStatusLogResponseMapper;
import com.pragma.plazacomidas.traceability.application.mapper.IOrderTimingResponseMapper;
import com.pragma.plazacomidas.traceability.domain.api.ITraceabilityServicePort;
import com.pragma.plazacomidas.traceability.domain.model.OrderStatusLogModel;
import com.pragma.plazacomidas.traceability.domain.model.OrderTimingModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TraceabilityHandler implements ITraceabilityHandler {

    private final ITraceabilityServicePort traceabilityServicePort;
    private final IOrderStatusLogRequestMapper orderStatusLogRequestMapper;
    private final IOrderStatusLogResponseMapper orderStatusLogResponseMapper;
    private final IOrderTimingResponseMapper orderTimingResponseMapper;

    @Override
    public void logStatusChange(OrderStatusLogRequestDto orderStatusLogRequestDto) {
        OrderStatusLogModel orderStatusLogModel = orderStatusLogRequestMapper.toOrderStatusLog(orderStatusLogRequestDto);
        traceabilityServicePort.logStatusChange(orderStatusLogModel);
    }

    @Override
    public List<OrderStatusLogResponseDto> getTraceabilityByOrderId(Long orderId, Long authenticatedClientId) {
        List<OrderStatusLogModel> logs = traceabilityServicePort.getTraceabilityByOrderId(orderId, authenticatedClientId);
        return orderStatusLogResponseMapper.toResponseList(logs);
    }

    @Override
    public List<OrderTimingResponseDto> getOrderTimings(OrderTimingsRequestDto orderTimingsRequestDto) {
        List<OrderTimingModel> timings = traceabilityServicePort.getOrderTimings(orderTimingsRequestDto.getOrderIds());
        return orderTimingResponseMapper.toResponseList(timings);
    }
}
