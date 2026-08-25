package com.pragma.plazacomidas.traceability.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.plazacomidas.traceability.application.dto.response.OrderTimingResponseDto;
import com.pragma.plazacomidas.traceability.domain.model.OrderTimingModel;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderTimingResponseMapper {

    OrderTimingResponseDto toResponse(OrderTimingModel orderTimingModel);

    List<OrderTimingResponseDto> toResponseList(List<OrderTimingModel> orderTimingModelList);
}
