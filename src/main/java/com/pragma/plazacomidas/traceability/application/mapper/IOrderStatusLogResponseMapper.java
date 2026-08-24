package com.pragma.plazacomidas.traceability.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.plazacomidas.traceability.application.dto.response.OrderStatusLogResponseDto;
import com.pragma.plazacomidas.traceability.domain.model.OrderStatusLogModel;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderStatusLogResponseMapper {

    OrderStatusLogResponseDto toResponse(OrderStatusLogModel orderStatusLogModel);

    List<OrderStatusLogResponseDto> toResponseList(List<OrderStatusLogModel> orderStatusLogModelList);
}
