package com.pragma.plazacomidas.traceability.infrastructure.input.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.plazacomidas.traceability.application.dto.request.OrderStatusLogRequestDto;
import com.pragma.plazacomidas.traceability.application.dto.response.OrderStatusLogResponseDto;
import com.pragma.plazacomidas.traceability.application.handler.ITraceabilityHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/traceability")
@RequiredArgsConstructor
public class TraceabilityRestController {

    private final ITraceabilityHandler traceabilityHandler;

    @Operation(summary = "Log an order status change (used for communication between microservices)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Status change logged", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid log data", content = @Content)
    })
    @PostMapping("/log")
    public ResponseEntity<Void> logStatusChange(@RequestBody OrderStatusLogRequestDto orderStatusLogRequestDto) {
        traceabilityHandler.logStatusChange(orderStatusLogRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get the status change history of one of the client's own orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Traceability returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderStatusLogResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Order does not belong to you or has no traceability", content = @Content),
            @ApiResponse(responseCode = "403", description = "Missing, invalid or insufficient token: only a CLIENT can query their own order traceability", content = @Content)
    })
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderStatusLogResponseDto>> getTraceabilityByOrderId(@PathVariable Long orderId) {
        Long authenticatedClientId = (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return ResponseEntity.ok(traceabilityHandler.getTraceabilityByOrderId(orderId, authenticatedClientId));
    }
}
