package com.pragma.plazacomidas.traceability.application.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTimingsRequestDto {
    private List<Long> orderIds;
}
