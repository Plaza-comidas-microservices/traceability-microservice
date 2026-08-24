package com.pragma.plazacomidas.traceability.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.plazacomidas.traceability.application.dto.request.OrderStatusLogRequestDto;
import com.pragma.plazacomidas.traceability.domain.model.OrderStatusLogModel;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrderStatusLogRequestMapper {

    OrderStatusLogModel toOrderStatusLog(OrderStatusLogRequestDto orderStatusLogRequestDto);
}
