package com.pragma.plazacomidas.traceability.domain.api;

import java.util.List;

import com.pragma.plazacomidas.traceability.domain.model.OrderStatusLogModel;
import com.pragma.plazacomidas.traceability.domain.model.OrderTimingModel;

public interface ITraceabilityServicePort {

    OrderStatusLogModel logStatusChange(OrderStatusLogModel orderStatusLogModel);

    List<OrderStatusLogModel> getTraceabilityByOrderId(Long orderId, Long authenticatedClientId);

    List<OrderTimingModel> getOrderTimings(List<Long> orderIds);
}
