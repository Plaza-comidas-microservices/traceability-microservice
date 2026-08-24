package com.pragma.plazacomidas.traceability.domain.api;

import java.util.List;

import com.pragma.plazacomidas.traceability.domain.model.OrderStatusLogModel;

public interface ITraceabilityServicePort {

    OrderStatusLogModel logStatusChange(OrderStatusLogModel orderStatusLogModel);

    List<OrderStatusLogModel> getTraceabilityByOrderId(Long orderId, Long authenticatedClientId);
}
