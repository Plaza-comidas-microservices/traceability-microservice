package com.pragma.plazacomidas.traceability.domain.usecase;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.pragma.plazacomidas.traceability.domain.api.ITraceabilityServicePort;
import com.pragma.plazacomidas.traceability.domain.exception.DomainException;
import com.pragma.plazacomidas.traceability.domain.model.OrderStatusLogModel;
import com.pragma.plazacomidas.traceability.domain.spi.IOrderStatusLogPersistencePort;

public class TraceabilityUseCase implements ITraceabilityServicePort {

    private final IOrderStatusLogPersistencePort orderStatusLogPersistencePort;

    public TraceabilityUseCase(IOrderStatusLogPersistencePort orderStatusLogPersistencePort) {
        this.orderStatusLogPersistencePort = orderStatusLogPersistencePort;
    }

    @Override
    public OrderStatusLogModel logStatusChange(OrderStatusLogModel orderStatusLogModel) {
        if (orderStatusLogModel.getOrderId() == null) {
            throw new DomainException("El id del pedido es obligatorio");
        } else if (orderStatusLogModel.getNewStatus() == null || orderStatusLogModel.getNewStatus().isBlank()) {
            throw new DomainException("El nuevo estado es obligatorio");
        }

        orderStatusLogModel.setChangedAt(Instant.now());

        return orderStatusLogPersistencePort.save(orderStatusLogModel);
    }

    @Override
    public List<OrderStatusLogModel> getTraceabilityByOrderId(Long orderId, Long authenticatedClientId) {
        List<OrderStatusLogModel> logs = orderStatusLogPersistencePort.findByOrderId(orderId);

        if (logs.isEmpty()) {
            throw new DomainException("No hay trazabilidad registrada para este pedido");
        } else if (!logs.get(0).getClientId().equals(authenticatedClientId)) {
            throw new DomainException("Este pedido no te pertenece");
        }

        return logs.stream()
                .sorted(Comparator.comparing(OrderStatusLogModel::getChangedAt))
                .collect(Collectors.toList());
    }
}
