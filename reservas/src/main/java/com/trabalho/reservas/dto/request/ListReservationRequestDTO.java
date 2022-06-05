package com.trabalho.reservas.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ListReservationRequestDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String resourcesType;
    private String userId;
}
