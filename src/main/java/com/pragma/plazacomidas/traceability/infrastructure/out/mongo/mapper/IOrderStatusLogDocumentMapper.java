package com.pragma.plazacomidas.traceability.infrastructure.out.mongo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.plazacomidas.traceability.domain.model.OrderStatusLogModel;
import com.pragma.plazacomidas.traceability.infrastructure.out.mongo.entity.OrderStatusLogDocument;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderStatusLogDocumentMapper {

    OrderStatusLogDocument toDocument(OrderStatusLogModel orderStatusLogModel);

    OrderStatusLogModel toModel(OrderStatusLogDocument orderStatusLogDocument);

    List<OrderStatusLogModel> toModelList(List<OrderStatusLogDocument> orderStatusLogDocumentList);
}
