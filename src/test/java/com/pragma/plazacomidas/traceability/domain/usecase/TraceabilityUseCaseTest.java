package com.pragma.plazacomidas.traceability.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pragma.plazacomidas.traceability.domain.exception.DomainException;
import com.pragma.plazacomidas.traceability.domain.model.OrderStatusLogModel;
import com.pragma.plazacomidas.traceability.domain.spi.IOrderStatusLogPersistencePort;

@ExtendWith(MockitoExtension.class)
class TraceabilityUseCaseTest {

    @Mock
    private IOrderStatusLogPersistencePort orderStatusLogPersistencePort;

    private TraceabilityUseCase traceabilityUseCase;

    private static final Long ORDER_ID = 1L;
    private static final Long CLIENT_ID = 1L;

    @BeforeEach
    void setUp() {
        traceabilityUseCase = new TraceabilityUseCase(orderStatusLogPersistencePort);
    }

    private OrderStatusLogModel buildLog(String previousStatus, String newStatus, Instant changedAt) {
        return new OrderStatusLogModel(null, ORDER_ID, CLIENT_ID, previousStatus, newStatus, changedAt);
    }

    @Test
    void shouldLogStatusChangeSuccessfullyWhenDataIsValid() {
        OrderStatusLogModel logModel = buildLog(null, "PENDIENTE", null);
        when(orderStatusLogPersistencePort.save(any(OrderStatusLogModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        traceabilityUseCase.logStatusChange(logModel);

        verify(orderStatusLogPersistencePort, times(1)).save(any(OrderStatusLogModel.class));
    }

    @Test
    void shouldThrowExceptionWhenLoggingWithoutOrderId() {
        OrderStatusLogModel logModel = buildLog(null, "PENDIENTE", null);
        logModel.setOrderId(null);

        DomainException exception = assertThrows(DomainException.class,
                () -> traceabilityUseCase.logStatusChange(logModel));

        assertEquals("El id del pedido es obligatorio", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLoggingWithoutNewStatus() {
        OrderStatusLogModel logModel = buildLog(null, "", null);

        DomainException exception = assertThrows(DomainException.class,
                () -> traceabilityUseCase.logStatusChange(logModel));

        assertEquals("El nuevo estado es obligatorio", exception.getMessage());
    }

    @Test
    void shouldReturnTraceabilitySortedByDateWhenOrderBelongsToClient() {
        OrderStatusLogModel readyLog = buildLog("EN_PREPARACION", "LISTO", Instant.now());
        OrderStatusLogModel pendingLog = buildLog(null, "PENDIENTE", Instant.now().minusSeconds(60));
        when(orderStatusLogPersistencePort.findByOrderId(ORDER_ID)).thenReturn(List.of(readyLog, pendingLog));

        List<OrderStatusLogModel> result = traceabilityUseCase.getTraceabilityByOrderId(ORDER_ID, CLIENT_ID);

        assertEquals(2, result.size());
        assertEquals("PENDIENTE", result.get(0).getNewStatus());
        assertEquals("LISTO", result.get(1).getNewStatus());
    }

    @Test
    void shouldThrowExceptionWhenOrderHasNoTraceability() {
        when(orderStatusLogPersistencePort.findByOrderId(ORDER_ID)).thenReturn(List.of());

        DomainException exception = assertThrows(DomainException.class,
                () -> traceabilityUseCase.getTraceabilityByOrderId(ORDER_ID, CLIENT_ID));

        assertEquals("No hay trazabilidad registrada para este pedido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOrderDoesNotBelongToClient() {
        OrderStatusLogModel pendingLog = buildLog(null, "PENDIENTE", Instant.now());
        pendingLog.setClientId(999L);
        when(orderStatusLogPersistencePort.findByOrderId(ORDER_ID)).thenReturn(List.of(pendingLog));

        DomainException exception = assertThrows(DomainException.class,
                () -> traceabilityUseCase.getTraceabilityByOrderId(ORDER_ID, CLIENT_ID));

        assertEquals("Este pedido no te pertenece", exception.getMessage());
    }
}
