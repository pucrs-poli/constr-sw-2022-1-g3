package com.trabalho.reservas.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationRequestDTO {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean enabled;
    private String lessonId;
    private String resourceId;
    private String userId;
}
