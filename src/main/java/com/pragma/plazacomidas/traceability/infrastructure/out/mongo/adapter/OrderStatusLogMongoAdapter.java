package com.pragma.plazacomidas.traceability.infrastructure.out.mongo.adapter;

import java.util.List;

import com.pragma.plazacomidas.traceability.domain.model.OrderStatusLogModel;
import com.pragma.plazacomidas.traceability.domain.spi.IOrderStatusLogPersistencePort;
import com.pragma.plazacomidas.traceability.infrastructure.out.mongo.entity.OrderStatusLogDocument;
import com.pragma.plazacomidas.traceability.infrastructure.out.mongo.mapper.IOrderStatusLogDocumentMapper;
import com.pragma.plazacomidas.traceability.infrastructure.out.mongo.repository.IOrderStatusLogMongoRepository;

public class OrderStatusLogMongoAdapter implements IOrderStatusLogPersistencePort {

    private final IOrderStatusLogMongoRepository orderStatusLogMongoRepository;
    private final IOrderStatusLogDocumentMapper orderStatusLogDocumentMapper;

    public OrderStatusLogMongoAdapter(IOrderStatusLogMongoRepository orderStatusLogMongoRepository,
        IOrderStatusLogDocumentMapper orderStatusLogDocumentMapper) {
        this.orderStatusLogMongoRepository = orderStatusLogMongoRepository;
        this.orderStatusLogDocumentMapper = orderStatusLogDocumentMapper;
    }

    @Override
    public OrderStatusLogModel save(OrderStatusLogModel orderStatusLogModel) {
        OrderStatusLogDocument document = orderStatusLogDocumentMapper.toDocument(orderStatusLogModel);
        OrderStatusLogDocument savedDocument = orderStatusLogMongoRepository.save(document);
        return orderStatusLogDocumentMapper.toModel(savedDocument);
    }

    @Override
    public List<OrderStatusLogModel> findByOrderId(Long orderId) {
        return orderStatusLogDocumentMapper.toModelList(orderStatusLogMongoRepository.findByOrderId(orderId));
    }
}
