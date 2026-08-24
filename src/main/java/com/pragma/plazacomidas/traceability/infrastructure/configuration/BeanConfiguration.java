package com.pragma.plazacomidas.traceability.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pragma.plazacomidas.traceability.domain.api.ITraceabilityServicePort;
import com.pragma.plazacomidas.traceability.domain.spi.IOrderStatusLogPersistencePort;
import com.pragma.plazacomidas.traceability.domain.usecase.TraceabilityUseCase;
import com.pragma.plazacomidas.traceability.infrastructure.out.mongo.adapter.OrderStatusLogMongoAdapter;
import com.pragma.plazacomidas.traceability.infrastructure.out.mongo.mapper.IOrderStatusLogDocumentMapper;
import com.pragma.plazacomidas.traceability.infrastructure.out.mongo.repository.IOrderStatusLogMongoRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IOrderStatusLogMongoRepository orderStatusLogMongoRepository;
    private final IOrderStatusLogDocumentMapper orderStatusLogDocumentMapper;

    @Bean
    public IOrderStatusLogPersistencePort orderStatusLogPersistencePort() {
        return new OrderStatusLogMongoAdapter(orderStatusLogMongoRepository, orderStatusLogDocumentMapper);
    }

    @Bean
    public ITraceabilityServicePort traceabilityServicePort() {
        return new TraceabilityUseCase(orderStatusLogPersistencePort());
    }
}
