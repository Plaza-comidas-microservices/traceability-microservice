package com.pragma.plazacomidas.traceability.domain.spi;

import java.util.List;

import com.pragma.plazacomidas.traceability.domain.model.OrderStatusLogModel;

public interface IOrderStatusLogPersistencePort {

    OrderStatusLogModel save(OrderStatusLogModel orderStatusLogModel);

    List<OrderStatusLogModel> findByOrderId(Long orderId);
}
